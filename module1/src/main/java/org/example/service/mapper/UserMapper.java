package org.example.service.mapper;

import org.example.domain.User;
import org.example.error.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User stringToUser(String inputData) {
        String[] userData = inputData.split(";");
        if (userData.length<3) {
            throw new ValidationException("Invalid input data:" );
        }
        return new User(userData[0].trim(), userData[1].trim(), userData[2].trim());
    }
}
