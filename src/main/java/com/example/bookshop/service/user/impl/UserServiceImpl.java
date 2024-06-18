package com.example.bookshop.service.user.impl;

import com.example.bookshop.entity.dto.UserDto;
import com.example.bookshop.entity.user.UserEntity;
import com.example.bookshop.entity.user.UserRole;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUserByEmail(String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setDateOfSubscription(user.getDateOfSubscription());
        return userDto;
    }

    @Override
    public void subscribe(String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        user.setRole(UserRole.VIP);
        user.setDateOfSubscription(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public void checkSubscription(String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        if(user.getDateOfSubscription() != null) {
            if (LocalDate.now().isAfter(user.getDateOfSubscription().plusMonths(1))) {
                user.setRole(UserRole.USER);
                userRepository.save(user);
            }
        }
    }


}
