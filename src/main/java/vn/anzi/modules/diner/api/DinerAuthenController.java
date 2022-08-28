package vn.anzi.modules.diner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.anzi.diner.config.DinerAuthenStatusDefine;
import vn.anzi.modules.diner.service.DinerAuthenService;
import vn.anzi.utils.TimeUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/diner")
public class DinerAuthenController {
    /**
     * Table save diner entity. Authen_name use index to search more faster. Authen_pass is null. Diner connect http to
     * sv, sv save a sessionId to cookie with expire time is 8 hours.
     */
    @Autowired
    private DinerAuthenService dinerAuthenService;

    @PostMapping("/authen")
    public byte dinerAuthenticate(@CookieValue(value = "dinerId", required = false) String dinerId, HttpServletResponse response) {
        if (dinerId == null) {
            dinerId = String.valueOf(dinerAuthenService.getNewDiner());
            Cookie cookie = new Cookie("dinerId", dinerId);
            cookie.setMaxAge(TimeUtil.convertHourToSecond(8));
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            return DinerAuthenStatusDefine.EXPIRED;
        }

        return DinerAuthenStatusDefine.SUCCESS;
    }
}