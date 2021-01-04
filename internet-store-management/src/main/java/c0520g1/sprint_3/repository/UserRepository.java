package c0520g1.sprint_3.repository;


import c0520g1.sprint_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> findAllByStatusTrue();

    List<User> findAllByUsernameContaining(String inputSearch);

    List<User> findAllByUsernameContainingOrFullNameContainingOrEmailContaining(String username, String fullName, String email);
}
