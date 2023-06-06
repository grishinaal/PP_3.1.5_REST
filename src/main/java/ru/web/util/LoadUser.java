package ru.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.web.model.Role;
import ru.web.model.User;
import ru.web.service.RoleService;
import ru.web.service.UserService;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoadUser {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public LoadUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    @PostConstruct
    public void load() {

        roleService.addNewRole(new Role(null, "ROLE_USER"));
        roleService.addNewRole(new Role(null, "ROLE_ADMIN"));

        User admin = new User(null, "007@email.ru", "James", "Bond", LocalDate.of(1988, Month.MARCH, 31), "007", true, new HashSet<>());

        User user = new User(null, "Vesper@email.ru", "Vesper", "Lynd", LocalDate.of(1991, Month.APRIL, 14), "001", true, new HashSet<>());

        userService.addNewUser(admin);
        userService.addNewUser(user);

        userService.addRoleToUser("007@email.ru", "ROLE_ADMIN");
        userService.addRoleToUser("Vesper@email.ru", "ROLE_USER");
        userService.addRoleToUser("007@email.ru", "ROLE_USER");
    }

}
