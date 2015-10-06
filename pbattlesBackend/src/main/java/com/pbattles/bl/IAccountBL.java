package com.pbattles.bl;

import com.pbattles.entity.Account;
import com.pbattles.entity.LoginInfoDTO;
import com.pbattles.entity.RegistrationInfo;

/**
 * Created by Nazar_Sheremeta on 5/29/14.
 */
public interface IAccountBL {


    boolean accountWithGivenLoginExists(String name);
    void registerAccount(RegistrationInfo info);
    Account getAccountByNameAndPassword(LoginInfoDTO info);
}
