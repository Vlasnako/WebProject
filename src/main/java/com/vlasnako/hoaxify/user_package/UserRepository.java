package com.vlasnako.hoaxify.user_package;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInst, Long> {

}
