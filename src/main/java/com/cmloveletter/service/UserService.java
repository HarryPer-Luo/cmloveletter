package com.cmloveletter.service;

import com.cmloveletter.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    PageInfo<User> getAllUserByPage(int pageNo, int pageSize);
    User getUserByID(int id);
    User loginUser(String name, String pwd);
}
