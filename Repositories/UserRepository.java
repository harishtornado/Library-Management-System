package Repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Role;
import models.User;

public interface UserRepository {
    static List<User> users = new ArrayList<>(Arrays.asList(new User("Harish", "12345678", Role.ADMIN)));

    public static List<User> findAll() {
        return users;
    }

    public static User findById(int id) {
        return users.stream().filter(user -> user.getUser_id() == id).findFirst().orElse(null);
    }

    public static User findByName(String name) {
        return users.stream().filter(user -> user.getUser_name().equals(name)).findFirst().orElse(null);
    }

    public static void save(User user) {
        users.add(user);
    }

    public static void delete(int id) {
        users.removeIf(user -> user.getUser_id() == id);
    }
}
