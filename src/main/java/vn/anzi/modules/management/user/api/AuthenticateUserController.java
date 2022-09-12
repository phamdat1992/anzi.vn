package vn.anzi.modules.management.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.anzi.modules.management.user.dto.AuthenticateRequestDTO;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/user/authenticate")
public class AuthenticateUserController {
    @Autowired
    private AuthenticateUserService authenticateService;

    @Value("${authenticate.user.cookie.email}")
    private String authenticateUserCookieEmail;

    @Value("${authenticate.user.cookie.id}")
    private String authenticateUserCookieId;

    @PostMapping("")
    public ResponseEntity<Void> authenticateUser(@RequestBody AuthenticateRequestDTO authenticate, HttpServletRequest request) {
        try {
            UserEntity userEntity = this.authenticateService.getUser(authenticate.getEmail(), request);
            HttpCookie accessTokenCookie = authenticateService.generateTokenCookie(
                    this.authenticateUserCookieEmail,
                    userEntity.getEmail()
            );

            HttpCookie idTokenCookie = authenticateService.generateTokenCookie(
                    this.authenticateUserCookieId,
                    userEntity.getId().toString()
            );

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, idTokenCookie.toString())
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
