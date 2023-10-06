package com.vlasnako.hoaxify.user_package;

import com.vlasnako.hoaxify.GenericResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/api/1.0/users")
    GenericResponce createUser(@RequestBody UserInst userr) {
        userService.save(userr);
        return new GenericResponce("User saved");
    }
}
