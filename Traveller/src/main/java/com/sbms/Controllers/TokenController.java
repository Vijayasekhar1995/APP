package com.sbms.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbms.Entitys.TokenRequest;
import com.sbms.JWTConfiguration.JWTConfig;

@RestController
public class TokenController {

    @Autowired
    private JWTConfig jwtConfig;
    @Autowired
    private AuthenticationManager authenticationManager;

    

    @PostMapping("/getToken")
    public String generateToken(@RequestBody TokenRequest tokenRequest) throws Exception {
  
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(tokenRequest.getUserName(), tokenRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User not exist with name : " + tokenRequest.getUserName());
        }
        return "Bearer "+jwtConfig.generateToken(tokenRequest.getUserName());
    }
}
