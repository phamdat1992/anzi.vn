package vn.anzi.dinner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.anzi.dinner.config.DinnerAuthenStatusDefine;
import vn.anzi.dinner.service.DinnerAuthenService;
import vn.anzi.utils.TimeUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DinnerAuthenController {
    @Autowired
    private DinnerAuthenService dinnerAuthenService;

    @PostMapping("/dinner/authen")
    public byte dinerAuthenticate(@CookieValue(value = "authenName") String authenName, HttpServletResponse response) {
        if (authenName == null) {
            authenName = dinnerAuthenService.getNewDinner();
            Cookie cookie = new Cookie("authenName", authenName);
            cookie.setMaxAge(TimeUtil.convertHourToSecond(8));
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            return DinnerAuthenStatusDefine.EXPIRED;
        }

        return DinnerAuthenStatusDefine.SUCCESS;
    }
}