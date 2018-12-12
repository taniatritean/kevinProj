package com.kevin.web;

import com.kevin.domain.User;
import com.kevin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/{user}"}, method = RequestMethod.POST)
    public void saveUser(@PathVariable("user") User user) {
        LOGGER.info("user >> {}", user);

        userService.saveUser(user);
    }
}
