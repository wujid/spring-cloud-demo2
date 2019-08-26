package com.cloud.service;

import com.cloud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/22 11:31
 * @Description:
 */
@Component
public class ErrorUserServiceImpl implements IUserService {
    public boolean add(User user) {
        return false;
    }

    public User get(String id) {
        return new User().setName("user服务种的get已停止使用!");
    }

    public List<User> list() {
        List<User> list = new ArrayList<User>(10);
        User user = new User().setName("user服务种的list已停止使用!");
        list.add(user);
        return list;
    }
}