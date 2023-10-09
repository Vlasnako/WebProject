package com.vlasnako.hoaxify.user_package;

import com.vlasnako.hoaxify.GenericResponce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/api/1.0/users")
    GenericResponce createUser(@Valid @RequestBody UserInst userr) {
//        if (userr.getUsername() == null || userr.getDisplayName() == null) {
//            throw new UserNotValidException();
//        }
        userService.save(userr);
        return new GenericResponce("User saved");
    }
}
