package com.cloud.service.impl;

import com.cloud.dao.UserDao;
import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:19
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    public boolean add(User user) {
        return userDao.addUser(user);
    }

    public User get(String id) {
        return userDao.findById(id);
    }

    public List<User> list() {
        return userDao.findAll();
    }
}
