package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<Role> getListRoles();

    void add(User user);

    List<User> getListUsers();

    void delete(Long id);

    void update(User user);

    User getById(Long id);

    User getByUsername(String userName);
}
