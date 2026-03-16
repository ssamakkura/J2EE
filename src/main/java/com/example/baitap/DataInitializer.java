package com.example.baitap;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.baitap.model.Account;
import com.example.baitap.model.Role;
import com.example.baitap.repository.AccountRepository;
import com.example.baitap.repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AccountRepository accountRepository, 
                               RoleRepository roleRepository, 
                               PasswordEncoder passwordEncoder) {
        return args -> {

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));
            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(new Role(null, "USER")));

            
            if (accountRepository.findByUsername("admin").isEmpty()) {
                Account admin = new Account();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin")); 
                admin.setRoles(Set.of(adminRole));
                accountRepository.save(admin);
            }

            
            if (accountRepository.findByUsername("user").isEmpty()) {
                Account user = new Account();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("123456")); 
                user.setRoles(Set.of(userRole));
                accountRepository.save(user);
            }
        };
    }
}