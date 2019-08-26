package com.cloud.service;

import com.cloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 16:36
 * @Description:
 */
//@FeignClient(value = "provider-user-a")
@FeignClient(value = "provider-user-a", fallback = ErrorUserServiceImpl.class)
public interface IUserService {
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(User user);

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id);

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list();
}
