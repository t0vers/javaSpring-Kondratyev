package org.example;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.domain.Command;
import org.example.error.InputCommandException;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    public String executeCommand(String inputData) throws InputCommandException{
        if (inputData.isEmpty()) {
            throw new InputCommandException("Invalid command");
        }
        String[] params = inputData.split(" ",2);
        Command command = Command.stringToCommand(params[0].trim());
        switch (command) {
            case ADD_USER:
                return userService.add(params[1].trim());
            case DELETE_USER:
                return userService.delete(params[1].trim());
            case GET_ALL_USERS:
                return userService.getAll();
            case HELP:
                return userService.help();
            default:
                throw new InputCommandException("Invalid command");
        }
    }
}

