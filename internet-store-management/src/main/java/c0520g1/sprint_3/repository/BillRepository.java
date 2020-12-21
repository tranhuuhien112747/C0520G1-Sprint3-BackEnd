package c0520g1.sprint_3.repository;

import c0520g1.sprint_3.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
