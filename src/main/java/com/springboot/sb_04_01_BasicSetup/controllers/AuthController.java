package com.springboot.sb_04_01_BasicSetup.controllers;

import com.springboot.sb_04_01_BasicSetup.dto.LoginDTO;
import com.springboot.sb_04_01_BasicSetup.dto.LoginResponseDto;
import com.springboot.sb_04_01_BasicSetup.dto.SignUpDTO;
import com.springboot.sb_04_01_BasicSetup.dto.UserDTO;
import com.springboot.sb_04_01_BasicSetup.services.AuthService;
import com.springboot.sb_04_01_BasicSetup.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // autowired
    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

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
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDTO loginDto, HttpServletRequest request, HttpServletResponse response) {
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));   // Set https only for prod usage
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = Arrays.stream(request.getCookies())
                                    .filter(cookie -> "refreshToken".equals(cookie.getName()))
                                    .findFirst()
                                    .map(Cookie::getValue)
                                    .orElseThrow(() -> new AuthenticationServiceException("refreshToken not found"));


        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }
}
