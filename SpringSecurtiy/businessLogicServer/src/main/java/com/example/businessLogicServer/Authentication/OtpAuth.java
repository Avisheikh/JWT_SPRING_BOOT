package com.example.businessLogicServer.Authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OtpAuth extends UsernamePasswordAuthenticationToken {
    public OtpAuth(
            Object principal,
            Object credentials
    ){
        super(principal, credentials);
    }

    public OtpAuth(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities
    ){
        super(principal, credentials, authorities);
    }

}
