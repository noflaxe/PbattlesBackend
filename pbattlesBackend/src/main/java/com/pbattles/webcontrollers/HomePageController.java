package com.pbattles.webcontrollers;


import com.google.common.base.Strings;
import com.pbattles.entity.Room;
import com.pbattles.roomlogic.RandomRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;


/**
 * Created by Nazar_Sheremeta on 4/3/14.
 */
@Controller
public class HomePageController {



    @Autowired
    private RandomRoomService roomService;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String redirectToHome(){
        return "index";
    }

    @RequestMapping(value = "room", method = RequestMethod.GET)
    public String moveToTheWaitingRoom(@CookieValue(value = "account",defaultValue = "")String account,Model model) throws InterruptedException {
        Room room = roomService.getRandomRoom();

        System.out.println("Account is "+account);
        model.addAttribute("room",room);
        if(Strings.isNullOrEmpty(account))  {
            String name = "guest"+new Random().nextInt(1000*100);
            model.addAttribute("login",name);
        }  else {
            model.addAttribute("login",account);
        }

        return "room";
    }

}
