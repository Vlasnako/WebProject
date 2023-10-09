package com.vlasnako.hoaxify.user_package;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.Entity;
import org.hibernate.annotations.NotFound;

@Data
@Entity
public class UserInst {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String username;
    @NotNull
    private String displayName;
    private String password;
}
