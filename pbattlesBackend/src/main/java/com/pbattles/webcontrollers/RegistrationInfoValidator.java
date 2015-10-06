package com.pbattles.webcontrollers;

import com.pbattles.bl.IAccountBL;
import com.pbattles.entity.RegistrationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Nazar_Sheremeta on 5/29/14.
 */
@Component
public class RegistrationInfoValidator implements Validator {

    @Autowired
    private IAccountBL accountBL;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationInfo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationInfo info = (RegistrationInfo) o;
        System.out.println(info);
        if(info == null){
            errors.reject("name","validation fail");
        }    else {
        validateForAlreadyExistingUser(info,errors);
        validateForMatchingPasswords(info,errors);
        }
    }

    private void validateForMatchingPasswords(RegistrationInfo info,Errors e) {
        if(info.getPassword() == null || !info.getPassword().equals(info.getPasswordRepeat())){
            e.reject("password","Password does not match");
        }
    }

    private void validateForAlreadyExistingUser(RegistrationInfo info,Errors e) {
       if(accountBL.accountWithGivenLoginExists(info.getLogin())){
         e.reject("name","User with this login already exist");
        }

    }
}
