package com.pbattles.webcontrollers;

import com.pbattles.bl.IAccountBL;
import com.pbattles.entity.Account;
import com.pbattles.entity.LoginInfoDTO;
import com.pbattles.entity.RegistrationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nazar_Sheremeta on 5/29/14.
 */

@Controller
public class AccountInfoController {

    @Autowired
    private Validator accountValidator;

    @Autowired
    private IAccountBL accountBL;

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String registerAccount(@ModelAttribute RegistrationInfo info,BindingResult result,Model m){
        if(validateInput(info,result)){
            accountBL.registerAccount(info);
            m.addAttribute("message","Successfully registrated, Login now!");
            return "index";
        } else{
            m.addAttribute("warning","User with this login already exists or password didnt match");
            return "index";
        }

    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@ModelAttribute LoginInfoDTO loginInfo,HttpServletResponse response, Model m){
        Account account = accountBL.getAccountByNameAndPassword(loginInfo);
        System.out.println("Login DTO: "+loginInfo);
        System.out.println("Account found: "+account);
        if(account == null){
            m.addAttribute("warning","Invalid login or password");
            return "index";
        } else {
           response.addCookie(new Cookie("account",account.getName()));
            return "reloadmain";
        }

    }

    @RequestMapping(value="logout"/*,method = RequestMethod.POST*/)
    public String logout(HttpServletResponse response){
        response.addCookie(new Cookie("account",""));
        System.out.println("LOGOUT PRESSED");
        return "reloadmain";
    }

    @RequestMapping(value="/home")
    public void fillModelContainer(@ModelAttribute Account account,Model m){
        m.addAttribute("registrationInfo", new RegistrationInfo());
        m.addAttribute("loginInfo", new LoginInfoDTO());
    }

    private boolean validateInput(RegistrationInfo info,BindingResult result) {
        accountValidator.validate(info, result);
        System.out.println("Validation start!");
        if(result.hasErrors()){
            System.out.println("Validation fail!");
            return false;
        } else{
            System.out.println("Okay");
            return true;
        }
    }
}
