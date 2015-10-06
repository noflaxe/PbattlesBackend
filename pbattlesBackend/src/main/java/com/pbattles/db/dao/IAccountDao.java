package com.pbattles.db.dao;

import com.pbattles.entity.Account;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 04.06.14
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public interface IAccountDao extends CRUDDao<Account> {
    public Account findByIdAndPassword(Object id,String password);
}
