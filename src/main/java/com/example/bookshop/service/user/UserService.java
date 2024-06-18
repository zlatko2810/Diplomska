package com.example.bookshop.service.user;

import com.example.bookshop.entity.dto.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email) throws Exception;

    void subscribe(String email) throws Exception;

    void checkSubscription(String email) throws Exception;
}
