package c0520g1.sprint_3.repository;

import c0520g1.sprint_3.model.BillServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillServiceRepository extends JpaRepository<BillServices,Long> {
}
