package demo.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.backend.security.JwtUtil;
import demo.backend.service.UserService;

@RestController
@RequestMapping("/api")
public class RestAuth {

     @Value("${demo.backend.httpsecure}")
     boolean httpSecure;

     @Autowired
     AuthenticationManager authenticationManager;

     @Autowired
     JwtUtil jwtUtil;

     private PasswordEncoder passwordEncoder;
     private RedisService redisService;
     private UserService userService;
     private ObjectMapper objectMapper;



}
