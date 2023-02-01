package vn.anzi.modules.diner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;
import vn.anzi.modules.diner.entity.DinerEntity;
import vn.anzi.modules.diner.repository.DinerRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class AuthenticateDinerService {
    @Autowired
    private DinerRepository dinerRepository;

    @Value("${cookie-authenticate-diner-expiration-in-second}")
    private String authenticateTokenTimeLiveInSecond;

    @Value("${authenticate.diner.cookie.name}")
    private String authenticateDinerCookieName;

    @Value("${authenticate.diner.cookie.id}")
    private String authenticateDinerCookieId;

    @Transactional(rollbackFor = Exception.class)
    public DinerEntity createDiner(HttpServletRequest request) throws Exception {
        Cookie authenticateName = WebUtils.getCookie(request, this.authenticateDinerCookieName);
        Cookie id = WebUtils.getCookie(request, this.authenticateDinerCookieId);

        if (authenticateName != null || id != null) {
            throw new Exception("token is existed");
        }

        String randomToken = UUID.randomUUID().toString();
        DinerEntity dinerEntity = new DinerEntity();
        dinerEntity.setAuthenticateName(randomToken);
        return this.dinerRepository.save(dinerEntity);
    }

    public HttpCookie generateTokenCookie(String tokenId, String tokenValue) {
        return ResponseCookie.from(tokenId, tokenValue)
                //.httpOnly(true)
                //.sameSite("strict")
                //.secure(true)
                .maxAge(Integer.parseInt(this.authenticateTokenTimeLiveInSecond))
                .path("/api")
                .build();
    }

    public Long getUserIdFromCookie(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        for (Cookie obj : cookie) {
            if (obj.getName().equals(authenticateDinerCookieId)) {
                return Long.parseLong(obj.getValue());
            }
        }

        return null;
    }
}