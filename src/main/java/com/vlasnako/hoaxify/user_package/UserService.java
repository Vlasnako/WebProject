package com.vlasnako.hoaxify.user_package;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public UserInst save(UserInst userr) {
        userr.setPassword(passwordEncoder.encode(userr.getPassword()));
        return userRepository.save(userr);
    }
}
