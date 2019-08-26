package com.cloud.controller;

import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wujid
 * @Date: 2019/8/24 10:41
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userService.list();
    }

    @RequestMapping(value = "/user/discovery", method = RequestMethod.GET)
    public Map<String, Object> discovery() {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<String> list = discoveryClient.getServices();
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = discoveryClient.getInstances("provider-user");
        for (int i = 0; i < srvList.size(); i++) {
            ServiceInstance element = srvList.get(i);
            int index = i + 1;
            map.put(index + "-serviceId", element.getServiceId());
            map.put(index + "-host", element.getHost());
            map.put(index + "-port", element.getPort());
            map.put(index + "-url", element.getUri());
        }
        return map;
    }
}
