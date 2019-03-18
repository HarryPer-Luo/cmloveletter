package com.cmloveletter.service.impl;

import com.cmloveletter.dao.UserMapper;
import com.cmloveletter.entity.PageBean;
import com.cmloveletter.entity.User;
import com.cmloveletter.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository(value = "mapper") //表明该类是用来执行与数据库相关的操作
@CacheConfig(cacheNames = "user")   //有多个缓存操作
@Service    //表示为逻辑层
public class UserServiceImpl implements UserService {

    //自动注入mapper类，数据交互层
    @Autowired
    UserMapper mapper;

    /**
     * 查询所有的应用
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Cacheable(cacheNames = "user") //声明方法是可缓存的
    @Override
    public PageInfo<User> getAllUserByPage(int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> list = mapper.getAllUser();
        PageInfo<User> page = new PageInfo<>(list);//用PageInfo对结果进行包装
        return page;
    }

    @Override
    public User getUserByID(int id) {
        return null;
    }

    @Override
    public User loginUser(String name, String pwd) {
        return mapper.login(name,pwd);
    }


}
