package com.pbattles.roomlogic;

import com.pbattles.entity.Room;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Nazar_Sheremeta on 5/14/14.
 */
public class QueueBasedContainer implements AvialibleRooms {

    private static final int RANDOM_STRING_LENGTH = 32;
    private static final long REMOVAL_INTERVAL = 1000*10;
    private Long lastOperationTime = System.currentTimeMillis();
    private int usersInRoom;
    private Queue<Room> avialableRooms = new ConcurrentLinkedQueue<Room>();
    private ThreadPoolTaskScheduler scheduler;


    public void initSchedule(){
        scheduler.scheduleWithFixedDelay(new QueueCleaner(),REMOVAL_INTERVAL);
    }

    @Override
    public Room getAvialableRoom() {
        lastOperationTime = System.currentTimeMillis();
        Room currentRoom = avialableRooms.poll();
        if(currentRoom == null){
            fillContainerWithNewRoom();
            return getAvialableRoom();
        }
        return currentRoom;
    }

    private void fillContainerWithNewRoom() {
        String newRoomName = RandomStringUtils.randomAlphanumeric(RANDOM_STRING_LENGTH);
        Room newRoom = new Room(newRoomName,usersInRoom);
        addRoomToQueue(usersInRoom,newRoom);
    }

    private void addRoomToQueue(int numberOfTimes,Room room) {
        for(int i=0;i<numberOfTimes;i++){
            avialableRooms.add(room);
        }
    }

    public void setUsersInRoom(int usersInRoom) {
        this.usersInRoom = usersInRoom;
    }

    public void setScheduler(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    private class QueueCleaner implements Runnable {
        @Override
        public void run() {
            if(System.currentTimeMillis() - lastOperationTime > 1000*20)
            avialableRooms.clear();
        }
    }
}
