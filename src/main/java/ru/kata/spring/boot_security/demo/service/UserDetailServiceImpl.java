package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {     //вынесен в отдельный сервис юзердетейлс (Fix #5)
    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByName(String email) {
        return userRepository.getByName(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userPrimary = getByName(email);

        if (userPrimary == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", email));
        }

        return userPrimary; //возвращает целого юзера (Fix #5)

    }
}
