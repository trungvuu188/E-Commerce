package com.e_commerce.grocery_mart.initializer;

import com.e_commerce.grocery_mart.entity.Role;
import com.e_commerce.grocery_mart.entity.User;
import com.e_commerce.grocery_mart.repository.UserRepository;
import com.e_commerce.grocery_mart.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AdminAccountInitializer implements CommandLineRunner {

    UserRepository userRepository;
    AdminService adminService;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${admin.username}")
    String username;

    @NonFinal
    @Value("${admin.password}")
    String password;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.existsByUsername(username)) {
            log.info("Admin account already existed, skipping creation");
            return;
        }

        Role adminRole = adminService.getAdminRole();
        User adminUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(adminRole)
                .build();

        userRepository.save(adminUser);
        log.info("Admin account created successfully with username: admin");
    }
}
