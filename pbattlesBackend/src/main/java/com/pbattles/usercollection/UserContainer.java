package com.pbattles.usercollection;

import com.google.common.collect.Lists;
import com.pbattles.entity.UserInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Nazar_Sheremeta on 3/25/14.
 */
public class UserContainer implements IUserContainer {

    private Set<UserInfo> queuedUsers = Collections.newSetFromMap(new ConcurrentHashMap());

    @Override
    public void addUser(UserInfo info) {
        if(info != null) queuedUsers.add(info);
    }

    @Override
    public void removeUser(UserInfo info) {
        if(info != null) queuedUsers.remove(info);
    }

    @Override
    public List<UserInfo> getAll() {
        List<UserInfo> result = Lists.newArrayList(queuedUsers);
        return result;
    }


}
