package com.actify.ums.config;

import com.actify.ums.entity.Role;
import com.actify.ums.entity.Task;
import com.actify.ums.entity.User;
import com.actify.ums.repository.RoleRepository;
import com.actify.ums.repository.TaskRepository;
import com.actify.ums.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           TaskRepository taskRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Role adminRole = createRole("ADMIN");
        Role managerRole = createRole("MANAGER");
        Role userRole = createRole("USER");

        User admin = createUser(
                "Admin User",
                "admin@actify.com",
                Set.of(adminRole)
        );

        User manager = createUser(
                "Manager User",
                "manager@actify.com",
                Set.of(managerRole)
        );

        User user = createUser(
                "Normal User",
                "user@actify.com",
                Set.of(userRole)
        );

        createUser(
                "Multi Role User",
                "multi@actify.com",
                Set.of(managerRole, userRole)
        );

        if (taskRepository.count() == 0) {
            Task task1 = new Task();
            task1.setTitle("Develop Login API");
            task1.setDescription("Implement JWT based login API");
            task1.setAssignedTo(user);
            taskRepository.save(task1);

            Task task2 = new Task();
            task2.setTitle("Test User APIs");
            task2.setDescription("Test user profile and task APIs");
            task2.setAssignedTo(manager);
            taskRepository.save(task2);
        }
    }

    private Role createRole(String name) {
        return roleRepository.findByName(name)
                .orElseGet(() -> roleRepository.save(new Role(null, name)));
    }

    private User createUser(String name, String email, Set<Role> roles) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(passwordEncoder.encode("Password@123"));
                    user.setRoles(roles);
                    return userRepository.save(user);
                });
    }
}
