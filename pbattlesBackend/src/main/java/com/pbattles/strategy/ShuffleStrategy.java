package com.pbattles.strategy;

import com.pbattles.entity.Room;
import com.pbattles.usercollection.IUserContainer;

import java.util.List;

/**
 * Created by Nazar_Sheremeta on 3/25/14.
 */
public interface ShuffleStrategy {

    List<Room> shuffle(IUserContainer container);

}
