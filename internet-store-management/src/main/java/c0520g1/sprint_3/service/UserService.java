package c0520g1.sprint_3.service;

import c0520g1.sprint_3.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    void changePassWord(Long id, String password);
}
