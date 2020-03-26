package pers.kanarien.chatroom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pers.kanarien.chatroom.model.po.User;
import pers.kanarien.chatroom.model.vo.ResponseJson;

public interface SecurityService {

    ResponseJson login(String username, String password, HttpSession session, HttpServletRequest request);
    
    ResponseJson logout(HttpSession session);

    void addUserCilent(User userInfo);

    void removeFriend(User user);
}
