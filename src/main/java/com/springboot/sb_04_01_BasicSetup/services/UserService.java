package com.springboot.sb_04_01_BasicSetup.services;

import com.springboot.sb_04_01_BasicSetup.exceptions.ResourceNotFoundException;
import com.springboot.sb_04_01_BasicSetup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User with Email: "+ username +" not found."));
    }
}
