package c0520g1.sprint_3.repository;

import c0520g1.sprint_3.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

    @Query(value = "SELECT * FROM services",nativeQuery = true)
    List<Services> getAllService();

    @Transactional
    @Query(value = "DELETE FROM services WHERE services.id_service = ?", nativeQuery = true)
    Void deleteService(Long id);

    @Query(value = "INSERT INTO services(price,quantity,service_name,unit) value (?,?,?,?)", nativeQuery = true)
    Void createService(String price, String quantity, String service_name, String unit);

}
