package com.senla.training.kononovich.server.service;

import org.springframework.stereotype.Component;

import com.senla.training.kononovich.server.api.service.IUserHandler;

@Component
public class UserHandler implements IUserHandler {
    private Long userId;

    public UserHandler() {
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
