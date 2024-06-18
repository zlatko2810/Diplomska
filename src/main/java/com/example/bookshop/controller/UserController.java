package com.example.bookshop.controller;

import com.example.bookshop.entity.dto.UserDto;
import com.example.bookshop.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserDto> getUser(@RequestParam(name = "email") String email) throws Exception {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam(name = "email") String email) throws Exception {
        userService.subscribe(email);
        return ResponseEntity.ok("User subscribed");
    }
}
