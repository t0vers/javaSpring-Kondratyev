package org.example.module2.config;

import org.example.module2.domain.UserStorage;
import org.example.module2.util.UserParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("init")
public class InitConfig {
    @Autowired
    UserParser userParser;

    @Bean
    public UserStorage userStorage(){
        return new UserStorage(userParser.parseInitFile());
    }

}
