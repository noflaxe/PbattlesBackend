package com.pbattles.db.dao;

import com.pbattles.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 04.06.14
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AccountDao implements IAccountDao {

    @Autowired
    private MongoOperations mongoTemplate;

    private static final String UNIQUE_ID = "login";

    @Override
    public void insert(Account entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public Account findById(Object id) {
        Query searchQuery = createSearchQueryByUniqueId(id);
        return (Account) mongoTemplate.findOne(searchQuery, getEntityClass());
    }

    public Account findByIdAndPassword(Object id,String password){
        Query searchQuery = createSearchQueryByLoginAndPassword(id, password);
        return (Account) mongoTemplate.findOne(searchQuery, getEntityClass());
    }

    @Override
    public List<Account> findAll() {
        return mongoTemplate.findAll(getEntityClass());
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void remove(Account entity) {
        String id = entity.getName();
        Query searchQuery = createSearchQueryByUniqueId(id);
        mongoTemplate.remove(searchQuery, getEntityClass());
    }

    private Class getEntityClass() {
        return Account.class;
    }

    private Query createSearchQueryByLoginAndPassword(Object id, String password) {
        return new Query(Criteria.where(UNIQUE_ID).is(id).andOperator(Criteria.where("password").is(password)));
    }

    private Query createSearchQueryByUniqueId(Object id) {
        return new Query(Criteria.where(UNIQUE_ID).is(id));
    }
}
