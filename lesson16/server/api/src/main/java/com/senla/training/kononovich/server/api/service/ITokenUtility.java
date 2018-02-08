package com.senla.training.kononovich.server.api.service;

public interface ITokenUtility {
    String createToken(Long id);
    Long getUserIdByToken(String token);
}
