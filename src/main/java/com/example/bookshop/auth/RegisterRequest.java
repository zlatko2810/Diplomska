package com.example.bookshop.auth;

import com.example.bookshop.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    private String email;

    private String password;

    private UserRole userRole;
}