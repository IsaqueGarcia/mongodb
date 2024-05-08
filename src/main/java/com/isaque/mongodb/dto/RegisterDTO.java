package com.isaque.mongodb.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class RegisterDTO {

    private String login;
    private String password;
    private UserRoles role;

}
