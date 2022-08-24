package vn.anzi.modules.management.user.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import vn.anzi.modules.diner.repository.DinerRepository;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AuthenticateUserService {
    @Autowired
    private UserRepository userRepository;

    @Value("${cookie-authenticate-user-expiration-in-second}")
    private String authenticateTokenTimeLiveInSecond;

    @Value("${authenticate.user.cookie.email}")
    private String authenticateUserCookieEmail;

    @Value("${authenticate.user.cookie.id}")
    private String authenticateUserCookieId;

    public UserEntity getUser(String email, HttpServletRequest request) throws Exception {
        Cookie authenticateEmail = WebUtils.getCookie(request, this.authenticateUserCookieEmail);
        Cookie id = WebUtils.getCookie(request, this.authenticateUserCookieId);

        if (authenticateEmail != null && id != null) {
            String tempEmail = authenticateEmail.getValue();
            Long tempId = Long.parseLong(id.getValue());
            if (tempEmail.equals(email)) {
                UserEntity user = new UserEntity();
                user.setEmail(tempEmail);
                user.setId(tempId);
                return user;
            }
        }

        Optional<UserEntity> user;
        user = userRepository.findByEmail(email).stream().findFirst();
        if (user.isEmpty()) {
            return createUser(email);
        }

        return user.get();
    }

    private UserEntity createUser(String email) throws Exception {
        try {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("token is existed");
        }
    }

    public HttpCookie generateTokenCookie(String tokenId, String tokenValue) {
        return ResponseCookie.from(tokenId, tokenValue)
                .httpOnly(true)
                .maxAge(Integer.parseInt(this.authenticateTokenTimeLiveInSecond))
                .sameSite("strict")
                .secure(true)
                .path("/api")
                .build();
    }
}
