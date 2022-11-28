package ru.kata.spring.boot_security.demo.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    User findByName(String username);
    void delete(Long id);
    void update(User user);
    boolean add(User user);
    List<User> listUsers();
    User findById(Long id);
}
