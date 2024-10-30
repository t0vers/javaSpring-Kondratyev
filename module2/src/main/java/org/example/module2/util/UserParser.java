package org.example.module2.util;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.module2.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserParser {
    @Value("${init-file.path}")
    String filePath;

    public List<User> parseInitFile(){
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while (reader.ready()){
                users.add(parseUser(reader.readLine()));
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private User parseUser(String inputData){
        String[] data = inputData.split(";");
        return new User(data[0],data[1],Integer.parseInt(data[2]));
    }
}
