package com.isaque.mongodb.dto;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String login;
    private String password;
}
