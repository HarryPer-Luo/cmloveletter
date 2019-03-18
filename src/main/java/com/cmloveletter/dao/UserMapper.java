package com.cmloveletter.dao;

import com.cmloveletter.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> getAllUser();
    public User login(String uname ,String upwd);
    public void insertUser(User user);
    public void deleteUser(int uid);
}
