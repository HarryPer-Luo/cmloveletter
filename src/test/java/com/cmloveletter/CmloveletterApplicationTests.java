package com.cmloveletter;

import com.cmloveletter.dao.UserMapper;
import com.cmloveletter.entity.User;
import com.cmloveletter.service.UserService;
import com.cmloveletter.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmloveletterApplicationTests {

    @Autowired
    UserServiceImpl service;

    @Test
    public void contextLoads() {
        //int count = mapper.countUser();
        //System.out.println(list);
    }

}

