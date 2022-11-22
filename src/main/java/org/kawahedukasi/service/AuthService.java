package org.kawahedukasi.service;

import org.kawahedukasi.exception.ValidationException;
import org.kawahedukasi.model.User;
import org.kawahedukasi.model.dto.LoginRequest;
import org.kawahedukasi.model.dto.LoginResponse;
import org.kawahedukasi.util.FormatUtil;
import org.kawahedukasi.util.GeneralUtil;
import org.kawahedukasi.util.JwtUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@ApplicationScoped
public class AuthService {
    public Response login(LoginRequest request) throws NoSuchAlgorithmException {
        if(!FormatUtil.isPassword(request.password)){
            throw new ValidationException("INVALID_PASSWORD");
        }

        Optional<User> userOptional = User.findbyLoginName(request.loginName);
        if(userOptional.isEmpty()){
            throw new ValidationException("USER_NOT_FOUND");
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(GeneralUtil.hashPassword(request.password))){
            throw  new ValidationException("WRONG_PASSWORD");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.userData = user;
        loginResponse.token = JwtUtil.generateToken(user);

        return Response.ok(loginResponse).build();
    }
}
