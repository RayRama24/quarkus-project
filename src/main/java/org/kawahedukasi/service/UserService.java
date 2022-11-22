package org.kawahedukasi.service;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import org.kawahedukasi.exception.ValidationException;
import org.kawahedukasi.model.User;
import org.kawahedukasi.model.dto.UserRequest;
import org.kawahedukasi.util.FormatUtil;
import org.kawahedukasi.util.GeneralUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

@ApplicationScoped
public class UserService {
    public Response post(UserRequest request) throws NoSuchAlgorithmException {
        if(!User.isEmptyByLoginName(request.loginName)){
            throw new ValidationException("LOGIN_NAME_EXIST");
        }
        if(!FormatUtil.isPassword(request.password)){
            throw new ValidationException("INVALID_PASSWORD");
        }
        if(!FormatUtil.isEmail(request.email) || !FormatUtil.isAlphabet(request.fullName) ||
                !FormatUtil.isPhoneNumber(request.mobilePhoneNumber) || !FormatUtil.isPhoneNumber(request.workPhoneNumber)){
            throw new ValidationException("BAD_REQUEST");
        }

        persistUser(request);

        return Response.ok(new HashMap<>()).build();

    }

    public Response get(String loginName) {
        Optional<User> userOptional = User.findbyLoginName(loginName);
        if(userOptional.isEmpty()){
            throw new ValidationException("USER_NOT_FOUND");
        }
        return Response.ok(userOptional.get()).build();
    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public User persistUser(UserRequest request) throws NoSuchAlgorithmException {
        Optional<User> userOptional = User.findbyLoginName(request.loginName);
        User user;
        if(userOptional.isEmpty()){
            user = new User();
        }else {
            user = userOptional.get();
        }
        user.setLoginName(request.loginName);
        user.setPassword(GeneralUtil.hashPassword(request.password));
        user.setAddress(request.address);
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setMobilePhoneNumber(request.mobilePhoneNumber);
        user.setWorkPhoneNumber(request.workPhoneNumber);
        User.persist(user);

        return user;
    }


}
