package pers.kanarien.chatroom.dao;

import pers.kanarien.chatroom.model.po.User;
import pers.kanarien.chatroom.model.po.UserInfo;

public interface UserInfoDao {

    void loadUserInfo();
    
    UserInfo getByUsername(String username);
    
    UserInfo getByUserId(String userId);

    void addUserCilent(User userInfo);

    void removeFriend(User user);
}
