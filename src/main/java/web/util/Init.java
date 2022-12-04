package web.util;

import web.model.Role;
import web.service.UserService;
import web.model.User;
import web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User newUser = new User("User",
                                "User",
                                30,
                                "user@mail.com",
                                "user",
                                "user",
                                userSet);

        User admin = new User("Admin",
                                "Admin",
                                30,
                                "admin@mail.ru",
                                "admin",
                                "admin",
                                adminSet);

        userService.saveUser(newUser);
        userService.saveUser(admin);
    }
}
