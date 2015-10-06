package com.pbattles.response;

import com.google.common.collect.Maps;
import com.pbattles.entity.Room;
import com.pbattles.entity.UserInfo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Nazar_Sheremeta on 4/7/14.
 */
@Deprecated()
public class WebViewResponceHandler implements ResponseHandler {

    private Map<Long,Room> userRooms = Maps.newConcurrentMap();

    @Override
    public void handleIncomingRoom(Room room) {
     //  List<UserInfo> roomUsers = room.getCurrentUsers();
        List<UserInfo> roomUsers = Collections.EMPTY_LIST;
        for (UserInfo roomUser : roomUsers) {
            userRooms.put(roomUser.getUserId(),room);
        }
    }


    @Override
    public Room delivedRoomByUserId(Long id) {
        return userRooms.remove(id);
    }
}
