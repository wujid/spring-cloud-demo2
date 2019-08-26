package com.cloud.controller;

import com.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 19:55
 * @Description:
 */
@RestController
public class ConsumerController {

    private static final String REST_URL_PREFIX = "http://provider-user";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/user/add")
    public boolean add(User user) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/user/add", user, Boolean.class);
    }

    @RequestMapping(value = "/consumer/user/get/{id}")
    public User get(@PathVariable("id") String id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/user/get/" + id, User.class);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/user/list")
    public List<User> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/user/list", List.class);
    }
}