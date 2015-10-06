package com.pbattles.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 02.04.14
 * Time: 0:54
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "userinfo")
public class UserInfo {


    private Long userId;
    private Long sessionId;
    private String accountName;
    private List<Long> roomUsers; //TODO: rename this field to something like usersChattedWithToday

    public UserInfo() {
    }

    public UserInfo(Long userId, Long sessionId, String accountName, List<Long> roomUsers) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.accountName = accountName;
        this.roomUsers = roomUsers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo that = (UserInfo) o;

        if (accountName != null ? !accountName.equals(that.accountName) : that.accountName != null) return false;
        if (sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null) return false;
        if (!userId.equals(that.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", sessionId=" + sessionId +
                ", accountName='" + accountName + '\'' +
                ", roomUsers=" + roomUsers +
                '}';
    }

    public List<Long> getRoomUsers() {
        return roomUsers;
    }

    public void setRoomUsers(List<Long> roomUsers) {
        this.roomUsers = roomUsers;
    }
}
