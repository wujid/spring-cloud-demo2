package com.cloud.dao;

import com.cloud.model.User;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:14
 * @Description:
 */
public interface UserDao {
    public boolean addUser(User dept);

    public User findById(String id);

    public List<User> findAll();
}
