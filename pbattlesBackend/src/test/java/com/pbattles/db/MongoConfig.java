package com.pbattles.db;

import com.github.fakemongo.Fongo;
import com.pbattles.bl.AccountBL;
import com.pbattles.db.dao.AccountDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 24.04.16
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@ImportResource(value = "classpath:spring-config.xml")
public class MongoConfig {

    @Bean
    public AccountDao accountDao() throws Exception {
        AccountDao accountDao = new AccountDao();
        return accountDao;
    }

    @Bean
    public AccountBL accountBL() {
        return new AccountBL();
    }

    @Primary
    public MongoTemplate mongoTemplateOverride() throws Exception {
        return new MongoTemplate(new SimpleMongoDbFactory(new Fongo("pbattlesMocked").getMongo(), "pbattlesMocked"));
    }
}
