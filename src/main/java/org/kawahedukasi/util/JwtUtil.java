package org.kawahedukasi.util;

import io.smallrye.jwt.build.Jwt;
import org.kawahedukasi.model.User;

import java.time.Duration;
import java.util.Date;

public class JwtUtil {
    public static String generateToken(User user){
       return   Jwt.issuer("green-cafe-kawahedukasi")
               .issuedAt(new Date().toInstant())
               .expiresIn(Duration.ofHours(1))
               .subject(user.getLoginName())
               .groups("user")
               .claim("email", user.getEmail())
               .claim("mobilePhoneNumber", user.getMobilePhoneNumber())
               .claim("fullName", user.getFullName())
               .sign();
    }
}
