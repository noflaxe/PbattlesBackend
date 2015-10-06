package com.pbattles.platformTest;

import com.pbattles.entity.UserInfo;
import com.pbattles.usercollection.UserContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Nazar_Sheremeta on 3/25/14.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        final UserContainer container = (UserContainer) context.getBean("basicContainer");
        context.registerShutdownHook();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Long uniqueId = Double.doubleToLongBits(Math.random());
                     System.out.println(uniqueId+" was added");
                    container.addUser(new UserInfo(uniqueId,uniqueId,"",null));
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();

    }
}
