package com.pbattles.response;

import com.pbattles.entity.Room;

/**
 * Created by Nazar_Sheremeta on 3/24/14.
 */
@Deprecated
public interface ResponseHandler {

    void handleIncomingRoom(Room room);
    Room delivedRoomByUserId(Long id);
}
