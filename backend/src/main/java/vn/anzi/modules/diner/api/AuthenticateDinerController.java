package vn.anzi.modules.diner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.anzi.modules.diner.entity.DinerEntity;
import vn.anzi.modules.diner.services.AuthenticateDinerService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/diner/authenticate")
public class AuthenticateDinerController implements WebMvcConfigurer {

    @Autowired
    private AuthenticateDinerService authenticateService;

    @Value("${authenticate.diner.cookie.name}")
    private String authenticateDinerCookieName;

    @Value("${authenticate.diner.cookie.id}")
    private String authenticateDinerCookieId;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configure) {
        configure.setDefaultTimeout(86_400_000L);
    }

    @PostMapping("")
    public ResponseEntity<Void> authenticateDiner(HttpServletRequest request) {
        try {
            DinerEntity dinerEntity = authenticateService.createDiner(request);
            HttpCookie accessTokenCookie = authenticateService.generateTokenCookie(
                    this.authenticateDinerCookieName,
                    dinerEntity.getAuthenticateName()
            );

            HttpCookie idTokenCookie = authenticateService.generateTokenCookie(
                    this.authenticateDinerCookieId,
                    String.valueOf(dinerEntity.getId())
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