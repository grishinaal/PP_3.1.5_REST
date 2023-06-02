package ru.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.web.model.Role;
import ru.web.service.RoleServiceImpl;
import ru.web.service.UserServiceImpl;
import ru.web.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserServiceImpl userService, RoleServiceImpl roleService) {
        return args -> {
            roleService.addNewRole(new Role(null, "ROLE_USER"));
            roleService.addNewRole(new Role(null, "ROLE_ADMIN"));

            userService.addNewUser(new User(null, "007@email.ru", "James", "Bond", LocalDate.of(1988, Month.MARCH, 31), "007", true, new HashSet<>()));
            userService.addNewUser(new User(null, "Vesper@email.ru", "Vesper", "Lynd", LocalDate.of(1991, Month.APRIL, 14), "001", true, new HashSet<>()));

            userService.addRoleToUser("007@email.ru", "ROLE_ADMIN");
            userService.addRoleToUser("Vesper@email.ru", "ROLE_USER");
            userService.addRoleToUser("push@yandex.ru", "ROLE_USER");
        };
    }
}
