package com.example.jjwt.Service;

import com.example.jjwt.Entity.Otp;
import com.example.jjwt.Entity.User;
import com.example.jjwt.Repo.OtpRepo;
import com.example.jjwt.Repo.UserRepo;
import com.example.jjwt.Util.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OtpRepo otpRepo;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void auth(User user) {
        Optional<User> o = userRepo.findUserByUsername(user.getUsername());
        if (o.isPresent()) {
            User u = o.get();
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())){
                renewOtp(u);
            } else {
                throw new BadCredentialsException("Bad credentials");
            }

        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> userOtp = otpRepo.findUserByUsername(u.getUsername());
        if(userOtp.isPresent()) {
            Otp otp = userOtp.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepo.save(otp);
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp = otpRepo.findUserByUsername(otpToValidate.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if(otpToValidate.getCode().equals(otp.getCode())) {
                return true;

            }
        }
        return false;
    }
}
