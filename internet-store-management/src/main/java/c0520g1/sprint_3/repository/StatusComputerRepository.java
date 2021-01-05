package c0520g1.sprint_3.repository;

import c0520g1.sprint_3.model.StatusComputer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusComputerRepository extends JpaRepository<StatusComputer,Long> {
    StatusComputer findByStatusComputerName(String name);
}
