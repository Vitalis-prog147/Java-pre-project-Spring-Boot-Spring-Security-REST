package springBoot_task.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springBoot_task.dao.UserDao;
import springBoot_task.model.User;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    @Transactional
    public void edit(int id, User editUser) {
        userDao.edit(id, editUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

}