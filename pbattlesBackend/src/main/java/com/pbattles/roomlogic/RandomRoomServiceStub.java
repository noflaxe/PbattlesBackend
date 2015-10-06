package com.pbattles.roomlogic;

import com.pbattles.entity.Room;

/**
 * Created by Nazar_Sheremeta on 5/14/14.
 */
public class RandomRoomServiceStub implements RandomRoomService {
    @Override
    public Room getRandomRoom() {
        return new Room("stub",5);
    }
}
