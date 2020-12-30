package c0520g1.sprint_3.service.impl;

import c0520g1.sprint_3.model.User;
import c0520g1.sprint_3.repository.UserRepository;
import c0520g1.sprint_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByStatusTrue() {
        return userRepository.findAllByStatusTrue();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setStatus(false);
            userRepository.save(user);
        }
    }

    @Override
    public void changePassWord(Long id, String password) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(password);
            userRepository.save(user);
        }
    }
}
