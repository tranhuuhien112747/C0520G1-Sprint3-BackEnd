package c0520g1.sprint_3.repository;


import c0520g1.sprint_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
    User findUserByEmail(String email);

    Boolean existsByUsername(String username);

    List<User> findAllByStatusTrue();

    List<User> findAllByUsernameContaining(String inputSearch);

    List<User> findAllByUsernameContainingOrFullNameContainingOrEmailContaining(String username, String fullName, String email);
}
