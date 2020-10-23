package com.softhaxi.marves.core.service;

import java.util.Optional;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){ 
        Optional<User> userOptional = userRepository.getByUsername(username);
        if(userOptional.isPresent()){
            throw new UsernameNotFoundException("Username not found");
        }
        return userOptional.get();
    }
    
    public User findUserByUsernameAndPassword(String username, String password){
        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(username, password);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("Username not found");
        }
        return userOptional.get();
    }
}