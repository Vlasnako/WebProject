package com.vlasnako.hoaxify.user_package;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserInst {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String displayName;
    private String password;
}
