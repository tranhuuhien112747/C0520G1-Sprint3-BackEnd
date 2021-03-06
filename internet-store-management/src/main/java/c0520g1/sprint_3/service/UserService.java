package c0520g1.sprint_3.service;

import c0520g1.sprint_3.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    List<User> findAllByStatusTrue();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    void changePassWord(Long id, String password);

    List<User> searchUser(String inputSearch);

}
