package com.springboot.sb_04_01_BasicSetup.controllers;

import com.springboot.sb_04_01_BasicSetup.dto.LoginDTO;
import com.springboot.sb_04_01_BasicSetup.dto.SignUpDTO;
import com.springboot.sb_04_01_BasicSetup.dto.UserDTO;
import com.springboot.sb_04_01_BasicSetup.services.AuthService;
import com.springboot.sb_04_01_BasicSetup.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // autowired
    private final UserService userService;
    private final AuthService authService;
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignUpDTO signUpDto) {
        UserDTO userDTO = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto, HttpServletRequest request, HttpServletResponse response) {
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
