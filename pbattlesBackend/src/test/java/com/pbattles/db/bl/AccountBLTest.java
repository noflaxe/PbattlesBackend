package com.pbattles.db.bl;

import com.pbattles.bl.IAccountBL;
import com.pbattles.db.MongoConfig;
import com.pbattles.entity.Account;
import com.pbattles.entity.LoginInfoDTO;
import com.pbattles.entity.RegistrationInfo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 24.04.16
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */

@ActiveProfiles({"test"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class})
public class AccountBLTest {

    @Autowired
    private IAccountBL accountBL;

    private List<RegistrationInfo> mockedAccounts = Arrays.asList(
            new RegistrationInfo("mockedID", "account1", "pw1"),
            new RegistrationInfo("mockedID2", "account0", "pw"),
            new RegistrationInfo("mockedID3", "account2", "pw"),
            new RegistrationInfo("mockedID4", "account3", "pw2"),
            new RegistrationInfo("mockedID5", "account4", "pw3"),
            new RegistrationInfo("mockedID6", "account5", "pw4"),
            new RegistrationInfo("mockedID7", "account6", "pw5"),
            new RegistrationInfo("mockedID8", "account7", "pw6"));


    @BeforeClass
    public void init() {
        mockedAccounts.stream().forEach(account -> accountBL.registerAccount(account));
    }

    @Test
    public void isRegistredExists(){
        assertTrue(accountBL.accountWithGivenLoginExists("mockedID"));
    }

    @Test
    public void isRegistredNotExists(){
        assertFalse(accountBL.accountWithGivenLoginExists("WrongmockedID"));
    }

    @Test
    public void registerUser(){
       RegistrationInfo ri = new RegistrationInfo("mockedIDNEW","accountNEW","pwpwpwpw");
       accountBL.registerAccount(ri);
       assertTrue(accountBL.accountWithGivenLoginExists("mockedIDNEW"));
    }

    @Test
    public void getRegistratedUser(){
        Account account = accountBL.getAccountByNameAndPassword(new LoginInfoDTO("mockedID","pw1"));
        assertEquals("account1",account.getName());
        assertEquals("mockedID",account.getLogin());
    }

    @Test
    public void getRegistratedUserWrongPassword(){
        Account account = accountBL.getAccountByNameAndPassword(new LoginInfoDTO("mockedID","pwWRONG"));
        assertNull(account);
    }

    @Test
    public void getRegistratedUserDoesntExist(){
        Account account = accountBL.getAccountByNameAndPassword(new LoginInfoDTO("enigma","enigma"));
        assertNull(account);
    }

}
