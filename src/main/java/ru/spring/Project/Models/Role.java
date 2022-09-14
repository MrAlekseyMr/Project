package ru.spring.Project.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
    USER,ADMIN,AUTHUSER;
    public String getAuthority() {
        return name();
    }
}
