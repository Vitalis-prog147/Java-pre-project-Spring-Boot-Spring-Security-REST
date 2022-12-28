package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Init {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public Init(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.add(role1);
        roleService.add(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();

        allRoles.add(role1);
        allRoles.add(role2);
        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("admin", "admin", "Moscow", "admin@gmail.com", roleAdmin);
        User user2 = new User("user", "user",  "Saint-Petersburg", "user@gmail.com", roleUser);
        User user3 = new User("user2", "user2", "Moscow", "user2@gmail.com", roleUser);
        User user4 = new User("admin2", "admin2", "Saint-Petersburg", "admin2@gmail.com", allRoles);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
    }
}
