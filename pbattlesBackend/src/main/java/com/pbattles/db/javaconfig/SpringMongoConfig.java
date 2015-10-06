package com.pbattles.db.javaconfig;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Nazar_Sheremeta on 4/2/14.
 */
@Configuration
public class SpringMongoConfig  {

    private final static String dbName = "pbattles";

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception{
        return new SimpleMongoDbFactory(new MongoClient(),dbName);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception{
        return new MongoTemplate(mongoDbFactory());
    }

}
