package com.cloud.controller;

import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 19:55
 * @Description:
 */
@RestController
public class ConsumerController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/consumer/user/add")
    public boolean add(User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/consumer/user/get/{id}")
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/user/list")
    public List<User> list() {
        return userService.list();
    }
}