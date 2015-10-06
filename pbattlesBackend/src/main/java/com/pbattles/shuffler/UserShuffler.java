package com.pbattles.shuffler;

import com.pbattles.entity.Room;
import com.pbattles.entity.UserInfo;
import com.pbattles.response.ResponseHandler;
import com.pbattles.strategy.RoomValidator;
import com.pbattles.strategy.ShuffleStrategy;
import com.pbattles.usercollection.IUserContainer;
import com.pbattles.usercollection.UserContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nazar_Sheremeta on 3/24/14.
 */
@Deprecated
public class UserShuffler {

    private IUserContainer container;
    private ShuffleStrategy shuffleStrategy;
    private ResponseHandler responseHandler;
    private ThreadPoolTaskScheduler scheduler;
    private RoomValidator roomValidator;
    private static final long POLL_TIME_DELAY = 1000L;

    public void init(){
        //DISABLED BECAUSE OF DEPRECATION
        //scheduler.scheduleWithFixedDelay(new ScheduleShuffleTask(),POLL_TIME_DELAY);
    }


    public void setShuffleStrategy(ShuffleStrategy shuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy;
    }

    public void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public void setScheduler(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setContainer(UserContainer container) {
        this.container = container;
    }

    public void setRoomValidator(RoomValidator roomValidator) {
        this.roomValidator = roomValidator;
    }


    private class ScheduleShuffleTask implements Runnable{
        @Override
        public void run() {
            List<Room> shuffledRooms = shuffleStrategy.shuffle(container);
                handleShuffledRooms(shuffledRooms);
        }
    }

    private void handleShuffledRooms(List<Room> shuffledRooms) {
        for (Room shuffledRoom : shuffledRooms) {
            if(roomValidator.validate(shuffledRoom)){
                responseHandler.handleIncomingRoom(shuffledRoom);
                removeUsersFromQueue(shuffledRoom);
            }
        }
    }

    private void removeUsersFromQueue(Room shuffledRoom) {
      //  List<UserInfo> userInfoList = shuffledRoom.getCurrentUsers();
        List<UserInfo> userInfoList = Collections.EMPTY_LIST; // BREAKS LOGIC, BUT CODE WILL GET DELETED ANYWAY
        for (UserInfo userInfo : userInfoList) {
            container.removeUser(userInfo);
        }
    }
}
