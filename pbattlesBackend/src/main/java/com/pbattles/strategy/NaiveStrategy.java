package com.pbattles.strategy;

import com.google.common.collect.Lists;
import com.pbattles.entity.Room;
import com.pbattles.entity.UserInfo;
import com.pbattles.usercollection.IUserContainer;

import java.util.List;

/**
 * Created by Nazar_Sheremeta on 3/25/14.
 */
public class NaiveStrategy implements ShuffleStrategy {
    private final int DEFAULT_ROOM_CAPACITY = 4;
    private final int FIRST_ELEMENT = 0;

    private int minimumUsersInRoom;
    private int roomCapacity = DEFAULT_ROOM_CAPACITY;


    @Override
    public List<Room> shuffle(IUserContainer container) {
        List<UserInfo> userPool = container.getAll();
        List<Room> result = shuffleUsersByNewRooms(userPool);
        return result;
    }

    private List<Room> shuffleUsersByNewRooms(List<UserInfo> userPool) {
        List<Room> result = Lists.newArrayList();
        while (userPool.size() >= minimumUsersInRoom) {
            Room room = fillRoomWithMinimalNumberOfUsers(userPool);
            result.add(room);
        }
        return result;
    }

    private Room fillRoomWithMinimalNumberOfUsers(List<UserInfo> userPool) {
        List<UserInfo> roomUsers = Lists.newLinkedList();
        for (int i = 0; i < minimumUsersInRoom; i++) {
            roomUsers.add(userPool.remove(FIRST_ELEMENT));
        }
       // return new Room(roomUsers,roomCapacity);
        return null;
    }

    public void setMinimumUsersInRoom(int minimumUsersInRoom) {
        validateInput(minimumUsersInRoom);
        this.minimumUsersInRoom = minimumUsersInRoom;
    }

    private void validateInput(int minimumUsersInRoom) {
        if (minimumUsersInRoom > roomCapacity || minimumUsersInRoom < 0) return;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
}
