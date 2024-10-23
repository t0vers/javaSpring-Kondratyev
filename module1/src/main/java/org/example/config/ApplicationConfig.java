package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.UserController;
import org.example.domain.Contact;
import org.example.service.FileService;
import org.example.service.UserService;
import org.example.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class ApplicationConfig {
    @Autowired
    private Contact contacts;
    @Autowired
    private FileService fileService;

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserService userService() {
        return new UserService(userMapper(), contacts,fileService);
    }

    @Bean
    public UserController userController() {
        return new UserController(userService());
    }
}
