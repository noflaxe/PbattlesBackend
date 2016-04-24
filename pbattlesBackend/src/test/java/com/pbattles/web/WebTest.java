package com.pbattles.web;

import com.pbattles.bl.AccountBL;
import com.pbattles.entity.Account;
import com.pbattles.entity.LoginInfoDTO;
import com.pbattles.entity.RegistrationInfo;
import com.pbattles.main.Main;
import com.pbattles.roomlogic.RandomRoomServiceStub;
import com.pbattles.webcontrollers.AccountInfoController;
import com.pbattles.webcontrollers.HomePageController;
import com.pbattles.webcontrollers.RegistrationInfoValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 24.04.16
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Main.class})
public class WebTest {

    private MockMvc mockMvc;

    private HomePageController homePageController = new HomePageController();

    private AccountInfoController accountInfoController = new AccountInfoController();

    private AccountBL mockedBL = Mockito.mock(AccountBL.class);

    @Before
    public void setup() {
        when(mockedBL.accountWithGivenLoginExists(any(String.class))).thenReturn(false);
        when(mockedBL.getAccountByNameAndPassword(any(LoginInfoDTO.class))).thenReturn(new Account("webName", "webLogin", "hiddenpw"));
        RegistrationInfoValidator registrationInfoValidator = new RegistrationInfoValidator();
        registrationInfoValidator.setAccountBL(mockedBL);
        accountInfoController.setAccountBL(mockedBL);
        accountInfoController.setRegistrationInfoValidator(registrationInfoValidator);
        homePageController.setRoomService(new RandomRoomServiceStub());
        this.mockMvc = standaloneSetup(homePageController, accountInfoController).defaultRequest(get("/")).build();
    }

    @Test
    public void homeRoute() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andExpect(forwardedUrl("index")).
                andExpect(model().attributeDoesNotExist("warning")).
                andExpect(status().isOk());
    }

    @Test
    public void logoutRoute() throws Exception {
        this.mockMvc.perform(get("/logout"))
                .andExpect(forwardedUrl("reloadmain")).
                andExpect(status().isOk());
    }

    @Test
    public void registrateRoute() throws Exception {
        this.mockMvc.perform(post("/register").param("login", "login").param("name", "name").param("password", "123").param("passwordRepeat", "123")).
                andExpect(model().attributeDoesNotExist("warning")).
                andExpect(model().attributeExists("message")).
                andExpect(forwardedUrl("index")).
                andExpect(status().isOk());

        verify(mockedBL, atLeastOnce()).registerAccount(any(RegistrationInfo.class));
    }

    @Test
    public void registrateRoutePasswordNotMatch() throws Exception {
        this.mockMvc.perform(post("/register").param("login", "login").param("name", "name").param("password", "123").param("passwordRepeat", "124")).
                andExpect(model().attributeExists("warning")).
                andExpect(model().attributeDoesNotExist("message")).
                andExpect(forwardedUrl("index")).
                andExpect(status().isOk());

        verify(mockedBL, never()).registerAccount(any(RegistrationInfo.class));
    }


    @Test
    public void registrateRouteUserAlreadyExist() throws Exception {
        when(mockedBL.accountWithGivenLoginExists(any(String.class))).thenReturn(true);

        this.mockMvc.perform(post("/register").param("login", "login").param("name", "name").param("password", "123").param("passwordRepeat", "124")).
                andExpect(model().attributeExists("warning")).
                andExpect(model().attributeDoesNotExist("message")).
                andExpect(forwardedUrl("index")).
                andExpect(status().isOk());

        verify(mockedBL, never()).registerAccount(any(RegistrationInfo.class));
    }

    @Test
    public void loginRoute() throws Exception {
        this.mockMvc.perform(post("/login").param("login", "login").param("password", "123")).
                andExpect(model().attributeDoesNotExist("warning")).
                andExpect(cookie().exists("account")).
                andExpect(forwardedUrl("reloadmain")).
                andExpect(status().isOk());
    }


    @Test
    public void loginRouteFail() throws Exception {
        when(mockedBL.getAccountByNameAndPassword(any(LoginInfoDTO.class))).thenReturn(null);

        this.mockMvc.perform(post("/login").param("login", "login").param("password", "123")).
                andExpect(model().attributeExists("warning")).
                andExpect(cookie().doesNotExist("account")).
                andExpect(forwardedUrl("index")).
                andExpect(status().isOk());
    }

    @Test
    public void roomRoute() throws Exception {
        this.mockMvc.perform(get("/room")).
                andExpect(forwardedUrl("room")).
                andExpect(model().attributeExists("login"));
    }


}
