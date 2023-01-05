package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RoleDaoImpl roleDao;
    private final UserDaoImpl userDao;

    @Autowired
    public UserServiceImpl(RoleDaoImpl roleDao, UserDaoImpl userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    @Override
    public List<Role> getListRoles() { return roleDao.getListRoles(); }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByUsername(String email) {
        return userDao.getByName(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userPrimary = getByUsername(email);

        if (userPrimary == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", email));
        }

        return new org.springframework.security.core.userdetails.User(userPrimary.getUsername(),
                                                                        userPrimary.getPassword(),
                                                                        userPrimary.getRoles());
    }
}