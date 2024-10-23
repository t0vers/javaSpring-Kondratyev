package org.example.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
public class Contact {
    HashMap<String, User> contacts;

    public void add(User user) {
        contacts.put(user.getEmail(), user);
    }

    public void delete(String email) {
        contacts.remove(email);
    }
}
