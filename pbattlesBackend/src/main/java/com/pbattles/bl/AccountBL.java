package com.pbattles.bl;

import com.pbattles.db.dao.IAccountDao;
import com.pbattles.entity.Account;
import com.pbattles.entity.LoginInfoDTO;
import com.pbattles.entity.RegistrationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nazar_Sheremeta on 5/29/14.
 */
@Component
public class AccountBL implements IAccountBL {

    @Autowired
    private IAccountDao registrationInfoDao;

    @Override
    public boolean accountWithGivenLoginExists(String name) {
        Account result = registrationInfoDao.findById(name);
        return result != null;
    }

    @Override
    public void registerAccount(RegistrationInfo info) {
        Account account = new Account(info.getName(),info.getLogin(),info.getPassword());
        registrationInfoDao.insert(account);
    }

    @Override
    public Account getAccountByNameAndPassword(LoginInfoDTO info) {
        return registrationInfoDao.findByIdAndPassword(info.getLogin(),info.getPassword());
    }
}
