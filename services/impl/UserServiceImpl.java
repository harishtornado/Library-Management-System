package services.impl;

import Repositories.UserRepository;
import models.Role;
import models.User;
import services.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = null;

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User getUser(String name) {
        return UserRepository.findByName(name);
    }

    @Override
    public boolean comparePassword(User user, String password) {
        return user.comparePassword(password);
    }

    @Override
    public User createUser(String name, String password, Role role) {
        User user = new User(name, password, role);
        UserRepository.save(user);
        return user;
    }

}
