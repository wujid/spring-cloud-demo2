package com.cloud.service.impl;

import com.cloud.dao.UserDao;
import com.cloud.model.User;
import com.cloud.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:19
 * @Description:
 */
@RestController
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(User user) {
        return userDao.addUser(user);
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "hystrixProccess")
    public User get(@PathVariable("id") String id) {
        User dept = userDao.findById(id);
        if (dept == null) {
            throw new NullPointerException("未找到该用户!");
        }
        return dept;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }

    public User hystrixProccess(@PathVariable("id") String id) {
        User user = new User().setName("该方法已被熔断!");
        return user;
    }

}