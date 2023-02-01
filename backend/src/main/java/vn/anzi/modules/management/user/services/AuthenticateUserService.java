package vn.anzi.modules.management.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;
import vn.anzi.modules.management.eatery.services.EateryService;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AuthenticateUserService {

    @Autowired
    private EateryService eateryService;

    @Autowired
    private UserRepository userRepository;

    @Value("${cookie-authenticate-user-expiration-in-second}")
    private String authenticateTokenTimeLiveInSecond;

    @Value("${authenticate.user.cookie.email}")
    private String authenticateUserCookieEmail;

    @Value("${authenticate.user.cookie.id}")
    private String authenticateUserCookieId;

    public UserEntity getUserFromCookie(HttpServletRequest request) {
        Cookie authenticateEmail = WebUtils.getCookie(request, this.authenticateUserCookieEmail);
        Cookie id = WebUtils.getCookie(request, this.authenticateUserCookieId);
        if (authenticateEmail != null && id != null) {
            UserEntity user = new UserEntity();
            user.setEmail(authenticateEmail.getValue());
            user.setId(Long.parseLong(id.getValue()));

            return user;
        }

        return null;
    }

    public UserEntity getUser(String email, HttpServletRequest request) {
        UserEntity user = this.getUserFromCookieByEmail(email, request);
        if (user != null) {
            return user;
        }

        user = this.getUserFromDbByEmail(email);
        if (user != null) {
            return user;
        }

        return this.createUser(email);
    }

    public UserEntity getUserFromCookieByEmail(String email, HttpServletRequest request) {
        UserEntity currentUser = this.getUserFromCookie(request);

        if (currentUser != null && currentUser.getEmail().equals(email)) {
            return currentUser;
        }

        return null;
    }

    public UserEntity getUserFromDbByEmail(String email) {
        Optional<UserEntity> user;
        user = userRepository.findByEmail(email).stream().findFirst();
        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public UserEntity createUser(String email) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setIsAdmin(false);
        return userRepository.save(user);
    }

    public HttpCookie generateTokenCookie(String tokenId, String tokenValue) {
        return ResponseCookie.from(tokenId, tokenValue)
                //.httpOnly(true)
                //.sameSite("strict")
                //.secure(true)
                .maxAge(Integer.parseInt(this.authenticateTokenTimeLiveInSecond))
                .path("/")
                .build();
    }
}