package c0520g1.sprint_3.repository;

import c0520g1.sprint_3.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
