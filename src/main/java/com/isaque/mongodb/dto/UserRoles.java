package com.isaque.mongodb.dto;

public enum UserRoles {
    ADMIN("Admin"),
    USER("User");

    private String role;

    UserRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
