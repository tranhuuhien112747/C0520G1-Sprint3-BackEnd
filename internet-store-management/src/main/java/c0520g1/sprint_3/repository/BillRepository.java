package c0520g1.sprint_3.repository;

import c0520g1.sprint_3.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findBillByUser_IdUser(Long id);
    List<Bill> findBillByStatusDisplayTrue();
}
