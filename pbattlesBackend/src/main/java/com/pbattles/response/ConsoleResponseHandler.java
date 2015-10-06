package com.pbattles.response;

import com.pbattles.entity.Room;

/**
 * Created by Nazar_Sheremeta on 3/25/14.
 */
public class ConsoleResponseHandler implements ResponseHandler{
    @Override
    public void handleIncomingRoom(Room room) {
        System.out.println("Handled : "+room);
    }

    @Override
    public Room delivedRoomByUserId(Long id) {
        return null;
    }
}
