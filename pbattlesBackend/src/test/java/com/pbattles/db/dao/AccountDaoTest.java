package com.pbattles.db.dao;

import com.pbattles.db.MongoConfig;
import com.pbattles.entity.Account;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 24.04.16
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */

@ActiveProfiles({"test"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class})
public class AccountDaoTest {

    @Qualifier("accountDao")
    @Autowired
    private IAccountDao accountDao;

    private List<Account> mockedAccounts = Arrays.asList(
            new Account("mockedID", "account1", "pw1"),
            new Account("mockedID2", "account0", "pw"),
            new Account("mockedID3", "account2", "pw"),
            new Account("mockedID4", "account3", "pw2"),
            new Account("mockedID5", "account4", "pw3"),
            new Account("mockedID6", "account5", "pw4"),
            new Account("mockedID7", "account6", "pw5"),
            new Account("mockedID8", "account7", "pw6"));

    @BeforeClass
    public void init() {
       mockedAccounts.stream().forEach(account -> accountDao.insert(account));
    }

    @Test
    public void findAllCount() {
        List<Account> result = accountDao.findAll();
        assertEquals(mockedAccounts.size(), result.size());
    }


    @Test
    public void findByName(){
        Account result = accountDao.findById("account1");
        assertEquals("account1", result.getLogin());
        assertEquals("mockedID", result.getName());
    }

    @Test
    public void findByNameDontExist(){
        Account result = accountDao.findById("accountNotExist");
        assertNull(result);
    }

    @Test
    public void findByNameAndPassword(){
        Account result = accountDao.findByIdAndPassword("account7", "pw6");
        assertEquals("account7", result.getLogin());
        assertEquals("mockedID8", result.getName());
    }

    @Test
    public void findByNameWrongPassword(){
        Account result = accountDao.findByIdAndPassword("account7","pw");
        assertNull(result);
    }

}
