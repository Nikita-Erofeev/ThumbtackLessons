package net.thumbtack.school.competition.service;

import net.thumbtack.school.competition.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserOnline {
    private static UserOnline instance;
    private Map<UUID, User> userOnline;

    private UserOnline() {
        userOnline = new HashMap<>();
    }

    public static UserOnline getInstance() {
        if (instance == null) {
            instance = new UserOnline();
        }
        return instance;
    }

    public void logIn(UUID token, User user) {
        userOnline.put(token, user);
    }

    public boolean logOut(UUID token) {
        if (userOnline.containsKey(token)) {
            userOnline.remove(token);
            return true;
        } else {
            return false;
        }
    }

    public User getUser(UUID token) {
        if (userOnline.containsKey(token)) {
            return userOnline.get(token);
        }
        return null;
    }

}
