package org.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.error.InputCommandException;

@RequiredArgsConstructor
@Getter
public enum Command {
    ADD_USER("add"),
    DELETE_USER("delete"),
    GET_ALL_USERS("get-all"),
    HELP("help");

    private final String command;

    public static Command stringToCommand(String inp){
        Command[] commands = Command.values();
        for(Command command:commands){
            if (inp.equals(command.getCommand())){
                return command;
            }
        }
        throw new InputCommandException("Invalid command");
    }
}
