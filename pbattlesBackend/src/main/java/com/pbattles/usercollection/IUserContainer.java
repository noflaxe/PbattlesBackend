package com.pbattles.usercollection;

import com.pbattles.entity.UserInfo;

import java.util.List;

/**
 * Created by Nazar_Sheremeta on 3/25/14.
 */
public interface IUserContainer {
    void addUser(UserInfo info);
    void removeUser(UserInfo info);
    List<UserInfo> getAll();
}
