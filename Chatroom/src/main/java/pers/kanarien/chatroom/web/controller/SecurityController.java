package pers.kanarien.chatroom.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.kanarien.chatroom.model.po.User;
import pers.kanarien.chatroom.model.vo.ResponseJson;
import pers.kanarien.chatroom.service.SecurityService;
import pers.kanarien.chatroom.util.Constant;

import java.io.Serializable;

@Controller
public class SecurityController {

    @Autowired
    SecurityService securityService;
    @Autowired
    private RedisTemplate redisTemplate;
    
    @RequestMapping(value = {"login", "/"}, method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson login(HttpSession session, HttpServletRequest request,
             String username,  String password) {

        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            if (StringUtils.isBlank(authorization)){
                return new ResponseJson().error("不存在该用户名");
            }else{
                String value =(String) redisTemplate.opsForValue().get(authorization);
                if (StringUtils.isBlank(value)){
                    return new ResponseJson().error("不存在该用户名");
                }
                Object o = redisTemplate.opsForValue().get(value);
                User userInfo =(User) redisTemplate.opsForValue().get(value);
                if (userInfo == null){
                    return new ResponseJson().error("不存在该用户名");
                }
                session.setAttribute(Constant.USER_TOKEN, userInfo.getUserId());
                securityService.addUserCilent(userInfo);
                return new ResponseJson().success();
            }
        }
        return securityService.login(username, password, session,request);
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson logout(HttpSession session,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)){
            String value =(String) redisTemplate.opsForValue().get(authorization);
           if (StringUtils.isNotBlank(value)){
               User user =(User) redisTemplate.opsForValue().get(value);
               securityService.removeFriend(user);
           }

        }
        return securityService.logout(session);
    }
}
