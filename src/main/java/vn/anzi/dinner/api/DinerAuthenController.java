package vn.anzi.diner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.anzi.diner.config.DinerAuthenStatusDefine;
//import vn.anzi.diner.service.DinerAuthenService;
import vn.anzi.utils.TimeUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DinerAuthenController {
    /**
     * Table save diner entity. Authen_name use index to search more faster. Authen_pass is null. Diner connect http to
     * sv, sv save a sessionId to cookie with expire time is 8 hours.
     */
//    @Autowired
//    private DinerAuthenService dinerAuthenService;

    @PostMapping("/diner/authen")
    public byte dinerAuthenticate(@CookieValue(value = "authenName") String authenName, HttpServletResponse response) {
//        if (authenName == null) {
//            authenName = dinerAuthenService.getNewDiner();
//            Cookie cookie = new Cookie("authenName", authenName);
//            cookie.setMaxAge(TimeUtil.convertHourToSecond(8));
//            cookie.setSecure(true);
//            cookie.setHttpOnly(true);
//            cookie.setPath("/");
//
//            response.addCookie(cookie);
//            return DinerAuthenStatusDefine.EXPIRED;
//        }

        return DinerAuthenStatusDefine.SUCCESS;
    }
}