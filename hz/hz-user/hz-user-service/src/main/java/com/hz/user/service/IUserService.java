package com.hz.user.service;

import com.hz.bean.User;

public interface IUserService {
    Boolean checkData(String data, Integer type);

    Boolean sendVerifyCode(String phone);

    Boolean register(User user, String code);

    User queryUser(String username, String password);
}
