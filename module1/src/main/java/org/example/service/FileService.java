package org.example.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.domain.User;
import org.example.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class FileService {
    final UserMapper userMapper;

    @Value("${file.path}")
    String filePath;

    public HashMap<String, User> initContacts() {
        HashMap<String, User> contacts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                User user = userMapper.stringToUser(reader.readLine());
                contacts.put(user.getEmail(), user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.newLine();
            writer.write(String.format("%s;%s;%s", user.getFullName(), user.getPhoneNumber(), user.getEmail()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
