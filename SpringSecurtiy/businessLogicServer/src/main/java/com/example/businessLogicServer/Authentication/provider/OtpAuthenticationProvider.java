package com.example.businessLogicServer.Authentication.provider;

import com.example.businessLogicServer.Authentication.OtpAuth;
import com.example.businessLogicServer.Authentication.proxy.AuthServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AuthServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());
        boolean result = proxy.sendOtp(username, code);
        if(result) {
            return new OtpAuth(username, code);
        } else {
            throw new BadCredentialsException("Bad Credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuth.class.isAssignableFrom(authentication);
    }
}
