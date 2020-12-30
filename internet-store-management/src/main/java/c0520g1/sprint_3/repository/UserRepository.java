package c0520g1.sprint_3.repository;


import c0520g1.sprint_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> findAllByStatusTrue();
}