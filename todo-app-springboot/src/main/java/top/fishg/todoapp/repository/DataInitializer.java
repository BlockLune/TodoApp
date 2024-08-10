package top.fishg.todoapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.fishg.todoapp.model.TodoUser;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TodoUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            TodoUser admin = new TodoUser();
            admin.setEmail("test_admin@example.com");
            admin.setPassword(passwordEncoder.encode("testpassword"));
            admin.setName("Test Admin");
            admin.setRoles(Set.of("ADMIN", "USER"));
            userRepository.save(admin);

            TodoUser user = new TodoUser();
            user.setEmail("test_user@example.com");
            user.setPassword(passwordEncoder.encode("testpassword"));
            user.setName("Test User");
            user.setRoles(Set.of("USER"));
            userRepository.save(user);

        }
    }
}
