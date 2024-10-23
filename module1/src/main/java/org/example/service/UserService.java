package org.example.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.domain.Contact;
import org.example.domain.Message;
import org.example.domain.User;
import org.example.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    UserMapper userMapper;
    Contact contact;
    FileService fileService;

    public String add(String inputData) {
        User user = userMapper.stringToUser(inputData);
        contact.add(user);
        fileService.addUser(user);
        return Message.ACCESS_ADD.getText();
    }

    public String delete(String email) {
        if (!contact.getContacts().containsKey(email))
            return Message.NOT_FOUND_USER.getText();
        contact.delete(email);
        return Message.ACCESS_DELETE.getText();
    }

    public String getAll() {
        if (contact.getContacts().isEmpty()) {
            return Message.NULL_CONTACTS.getText();
        }
        StringBuilder allUserString = new StringBuilder();
        for (User user : contact.getContacts().values()) {
            allUserString.append(user.toString()).append("\n");
        }
        return allUserString.toString();
    }

    public String help() {
        return Message.HELP.getText();
    }
}
