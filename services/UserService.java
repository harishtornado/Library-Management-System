package services;

import models.Role;
import models.User;

public interface UserService {

    User createUser(String name, String password, Role role);

    boolean comparePassword(User borrower, String password);

    User getUser(String name);
}
