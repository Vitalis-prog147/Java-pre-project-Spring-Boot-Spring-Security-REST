package springBoot_task.service;

import springBoot_task.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void save(User user);
    User get(int id);
    void edit(int id, User EditUser);
    void delete(int id);

}