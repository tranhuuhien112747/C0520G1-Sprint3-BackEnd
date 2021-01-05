package c0520g1.sprint_3.repository;

import c0520g1.sprint_3.model.Computer;
import c0520g1.sprint_3.model.StatusComputer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
    Computer findByComputerName(String name);
    List<Computer> findAllByStatusComputer_IdStatusComputer(Long id);
}
