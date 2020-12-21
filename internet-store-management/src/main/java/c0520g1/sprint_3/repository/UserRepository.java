package c0520g1.sprint_3.repository;


import c0520g1.sprint_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);
}
