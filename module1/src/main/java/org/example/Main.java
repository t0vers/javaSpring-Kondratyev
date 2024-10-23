package org.example;

import lombok.RequiredArgsConstructor;
import org.example.config.ApplicationConfig;
import org.example.domain.Message;
import org.example.error.InputCommandException;
import org.example.error.ValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@ComponentScan("org.example")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserController userController = context.getBean(UserController.class);
        System.out.println(Message.HELLO.getText());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                try {
                    System.out.println(userController.executeCommand(reader.readLine()));
                }catch (InputCommandException | ValidationException e){
                    System.out.println(e);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}