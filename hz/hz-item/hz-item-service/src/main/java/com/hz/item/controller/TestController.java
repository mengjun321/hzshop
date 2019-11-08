package com.hz.item.controller;

import com.hz.common.util.Dept;
import com.hz.common.util.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {
    @GetMapping("/test")
    public Object test(){

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("code",200);
        hashMap.put("msg","恭喜你,响应成功");
        String serialize = JsonUtils.serialize(new Dept().setLoc("sss").setDname("kkk").setDeptno(123));
        System.out.println("--->"+serialize);
        return  serialize;
    }


}
