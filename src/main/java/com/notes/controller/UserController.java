package com.notes.controller;

import com.notes.Domain.AuthRequest;
import com.notes.Domain.RestResponse;
import com.notes.Entity.UserInfo;
import com.notes.Service.JwtService;
import com.notes.Service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public String addUser(@Valid @RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/login")
    public RestResponse addUser(@Valid @RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()){
            return new RestResponse(true,jwtService.generateToken(authRequest.getUsername()),"Sucess");
        }else {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    }


}
