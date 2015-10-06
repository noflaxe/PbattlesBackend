package com.pbattles.db.dao;

import com.pbattles.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by Nazar_Sheremeta on 4/2/14.
 */
public class UserInfoDao implements IUserInfoDao {

    @Autowired
    private MongoOperations mongoTemplate;

    @Override
    public void insert(UserInfo entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public UserInfo findById(Object id) {
        Query searchQuery = createSearchQueryByUserId(id);
        return (UserInfo) mongoTemplate.findOne(searchQuery,getEntityClass());
    }

    @Override
    public List<UserInfo> findAll() {
        return mongoTemplate.findAll(getEntityClass());
    }

    @Override
    public void update(UserInfo entity) {
        throw new UnsupportedOperationException("Not sure how to do this optimally yet");
    }

    @Override
    public void remove(UserInfo entity) {
        Long id = entity.getUserId();
        Query searchQuery = createSearchQueryByUserId(id);
        mongoTemplate.remove(searchQuery,getEntityClass());
    }

    private Query createSearchQueryByUserId(Object id) {
        return new Query(Criteria.where("userId").is(id));
    }

    private Class getEntityClass(){
        return UserInfo.class;
    }

    public void setMongoTemplate(MongoOperations mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
