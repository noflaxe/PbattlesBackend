package com.pbattles.usercollection;

import com.pbattles.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

/**
 * Created by Nazar_Sheremeta on 3/25/14.
 */
public class UserContainerTest {

    private UserContainer container;

    @Before
    public void init() {
        container = new UserContainer();
    }

    @Test
    public void addIsNotFailing() {
        addUserInfoToContainer(-5L);
    }

    @Test
    public void addIsNotFailingOnNull() {
        container.addUser(null);
    }

    @Test
    public void addIsNotFailingOnRepeatableAdd() {
        addUserInfoToContainer(-5L, -5L);
    }

    @Test
    public void getAllReturnOnNoElements() {
        List result = container.getAll();
        assertListSize(result, 0);
    }

    @Test
    public void getAllReturnOnOneElement(){
        addUserInfoToContainer(0L);
        List result = container.getAll();
        assertListSize(result, 1);
    }

    @Test
    public void getAllReturnOnLotsOfElements(){
        addUserInfoToContainer(0L,2L,3L,4L,5L);
        List result = container.getAll();
        assertListSize(result, 5);
    }

    @Test
    public void getAllReturnOnAddNull(){
        container.addUser(null);
        List result = container.getAll();
        assertListSize(result, 0);
    }

    @Test
    public void getAllReturnOnLotsOfElementsAndNull(){
        addUserInfoToContainer(0L,2L,3L,4L,5L);
        container.addUser(null);
        List result = container.getAll();
        assertListSize(result, 5);
    }

    @Test
    public void removeUserOnNotExisting(){
        removeUserInfoFomContainer(0L);
        List result = container.getAll();
        assertListSize(result, 0);
    }

    @Test
    public void removeUserOnExistingElement(){
        addUserInfoToContainer(0L);
        removeUserInfoFomContainer(0L);
        List result = container.getAll();
        assertListSize(result, 0);
    }

    @Test
    public void removeUserOnExistingElements(){
        addUserInfoToContainer(0L,2L);
        removeUserInfoFomContainer(0L);
        List result = container.getAll();
        assertListSize(result, 1);
    }

    @Test
    public void removeUserOnNotExistingElements(){
        addUserInfoToContainer(0L,2L);
        removeUserInfoFomContainer(3L);
        List result = container.getAll();
        assertListSize(result, 2);
    }

    @Test
    public void removeNullOnExistingElements(){
        addUserInfoToContainer(0L,2L);
        container.removeUser(null);
        List result = container.getAll();
        assertListSize(result, 2);
    }

    // CHANGE LIST TO SET, ADD UNIQUALITY. check for remove null

    private void assertListSize(List result, int expectedSize) {
        int realSize = result.size();
        assertEquals(expectedSize, realSize);
    }

    private void removeUserInfoFomContainer(Long... sessionIds) {
        for (Long sessionId : sessionIds) {
            UserInfo info = buildUserInfo(sessionId);
            container.removeUser(info);
        }
    }

    private void addUserInfoToContainer(Long... sessionIds) {
        for (Long sessionId : sessionIds) {
            UserInfo info = buildUserInfo(sessionId);
            container.addUser(info);
        }
    }

    private UserInfo buildUserInfo(Long sessionId) {
        UserInfo info = new UserInfo();
        info.setSessionId(sessionId);
        return info;
    }
}
