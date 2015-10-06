package com.pbattles.roomlogic;

import com.pbattles.entity.Room;

/**
 * Created by Nazar_Sheremeta on 5/14/14.
 */
public class RandomRoomServiceImpl implements RandomRoomService {

    private AvialibleRooms roomContainer;

    @Override
    public Room getRandomRoom() {
        return roomContainer.getAvialableRoom();
    }

    public void setRoomContainer(AvialibleRooms roomContainer) {
        this.roomContainer = roomContainer;
    }
}
