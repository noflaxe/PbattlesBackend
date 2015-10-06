package com.pbattles.db.dao;

import com.pbattles.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 02.04.14
 * Time: 0:57
 * To change this template use File | Settings | File Templates.
 */
public interface UserInfoRepository extends MongoRepository<UserInfo,Long>{

    public UserInfo findByUserId(Long userId);
}
