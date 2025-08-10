package com.janzdev.restfulapi.config;

import com.janzdev.restfulapi.entity.User;
import com.janzdev.restfulapi.entity.Role;
import com.janzdev.restfulapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findUserByEmail("admin@ecommerce.com").isEmpty()) {
            User adminUser = User.builder()
                    .email("admin@ecommerce.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .firstName("User")
                    .lastName("Administrator")
                    .build();
            userRepository.save(adminUser);

        } else {
            System.out.println("Existing administrator user.");
        }
    }
}
