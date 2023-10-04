package com.vlasnako.hoaxify.user_package;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    public UserInst save(UserInst userr) {
        return userRepository.save(userr);
    }
}
