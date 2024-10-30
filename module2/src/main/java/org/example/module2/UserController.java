package org.example.module2;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.module2.domain.User;
import org.example.module2.domain.UserStorage;
import org.example.module2.eventlistener.event.AddUserEvent;
import org.example.module2.eventlistener.event.DeleteUserEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.UUID;

@ShellComponent
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    UserStorage userStorage;
    ApplicationEventPublisher applicationEventPublisher;

    @ShellMethod
    public String getAll() {
        if(userStorage.getAll().isEmpty()){
            return "List users is empty";
        }
        StringBuilder stringUsers=new StringBuilder();
        for (User user: userStorage.getAll()){
            stringUsers.append(user.toString()).append("\n");
        }
        return stringUsers.toString();
    }

    @ShellMethod
    public void add(String firstName, String secondName, int age) {
        User user = new User(firstName,secondName,age);
        applicationEventPublisher.publishEvent(new AddUserEvent(user.toString()));
        userStorage.add(user);
    }

    @ShellMethod
    public void deleteByUID(UUID uuid) {
        applicationEventPublisher.publishEvent(new DeleteUserEvent(uuid.toString()));
        userStorage.delete(uuid);
    }

    @ShellMethod
    public String deleteAll() {
        userStorage.deleteAll();
        return "Users storage cleared";
    }
}
