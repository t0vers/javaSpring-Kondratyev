package org.example.module2.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class UserStorage {
    private final HashMap<UUID, User> users;

    public UserStorage() {
        this.users = new HashMap<>();
    }

    public UserStorage(List<User> users) {
        this.users = new HashMap<>();
        for (User user : users) {
            add(user);
        }
    }

    public void add(User user) {
        users.put(user.getUid(), user);
    }

    public void delete(UUID uuid) {
        users.remove(uuid);
    }

    public void deleteAll() {
        users.clear();
    }

    public List<User> getAll() {
        return users.values().stream().toList();
    }
}
