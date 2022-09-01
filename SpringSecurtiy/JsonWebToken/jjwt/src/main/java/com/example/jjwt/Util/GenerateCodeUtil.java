package com.example.jjwt.Util;

import java.security.SecureRandom;

public final class GenerateCodeUtil {
    private GenerateCodeUtil(){}
    public static String generateCode() {
        String code;
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int c = random.nextInt(9000) + 1000;
            code = String.valueOf(c);
        } catch (Exception e) {
            throw new RuntimeException("Problem when generating the code");
        }
        return code;
    }
}
