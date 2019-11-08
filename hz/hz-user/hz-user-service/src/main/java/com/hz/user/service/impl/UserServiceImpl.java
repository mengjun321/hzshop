package com.hz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.bean.User;
import com.hz.common.util.CodecUtils;
import com.hz.common.util.NumberUtils;
import com.hz.user.mapper.UserMapper;
import com.hz.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private UserMapper userMapper;

    static final String KEY_PREFIX = "user:code:phone:";

    static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);



    @Override
    public Boolean checkData(String data, Integer type) {

        QueryWrapper<User> queryWrapper = new QueryWrapper();
        switch (type) {
            case 1:
                queryWrapper.eq("username",data);
                break;
            case 2:
                queryWrapper.eq("phone",data);
                break;
            default:
                return null;
        }

        int count = userMapper.selectCount(queryWrapper);
        return count==0;
    }

    @Override
    public Boolean sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);
        try {
            // 发送短信
            Map<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("code", code);
            this.amqpTemplate.convertAndSend("hz.sms.exchange", "sms.verify.code", msg);
            // 将code存入redis
            this.redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            logger.error("发送短信失败。phone：{}， code：{}", phone, code);
            return false;
        }
    }

    @Override
    public Boolean register(User user, String code) {
        String key = KEY_PREFIX + user.getPhone();
        //1.从redis中取出验证码
        String codeCache = this.redisTemplate.opsForValue().get(key);
        //2.检查验证码是否正确
        if(!codeCache.equals(code)){
            //不正确，返回
            return false;
        }
        user.setId(null);
        user.setCreated(new Date());
        user.setSalt("1234"); //盐值
        //3.密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(user.getUsername().trim(),user.getPassword().trim());
        user.setPassword(encodePassword);
        //4.写入数据库
        boolean result = this.userMapper.insert(user) == 1;
        //5.如果注册成功，则删掉redis中的code
        if (result){
            try{
                this.redisTemplate.delete(KEY_PREFIX + user.getPhone());
            }catch (Exception e){
                logger.error("删除缓存验证码失败，code:{}",code,e);
            }
        }
        return result;

    }

    @Override
    public User queryUser(String username, String password) {
        //1.查询
        //User record = new User();
        //record.setUsername(username);
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username",username);
        User user = this.userMapper.selectOne(query);

        //2.校验用户名
        if (user == null){
            return null;
        }
        //3. 校验密码
       Boolean flag  = CodecUtils.passwordBcryptDecode(username+password,user.getPassword());
        if (!flag){
            return null;
        }

        //4.用户名密码都正确
        return user;

    }

    public static void main(String[] args) {

        String code = NumberUtils.generateCode(8);
        System.out.println(code);


        //加密  用户名+密码
        String encode = CodecUtils.passwordBcryptEncode("xiaohua", "aa123456");
        System.out.println(encode);


        //解密  有户名密码+密码
        Boolean aBoolean = CodecUtils.passwordBcryptDecode("xiaohuaa123456","$2a$10$ckzFAFLSg5IGl3JE9VMyQOORj/pIe.0Pris6WN62hIYGd0jS9X2C.");
        System.out.println(aBoolean);

    }
}
