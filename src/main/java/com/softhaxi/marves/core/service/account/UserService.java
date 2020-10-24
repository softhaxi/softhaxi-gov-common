package com.softhaxi.marves.core.service.account;

import java.util.Optional;

import com.softhaxi.marves.core.domain.access.Role;
import com.softhaxi.marves.core.domain.access.UserRole;
import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.repository.access.RoleRepository;
import com.softhaxi.marves.core.repository.access.UserRoleRepository;
import com.softhaxi.marves.core.repository.account.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andryan Situngkir
 * @since 1
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Optional<User> findUserByUsernameAndPassword(String username, String password){
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    public User saveMobileUser(User user) {
        Role role = roleRepository.findByName("MOBILE").orElse(null);

        user = userRepository.save(user);
        if(role != null) {
            userRoleRepository.save(new UserRole(user, role));
        }

        return user;
    }
}