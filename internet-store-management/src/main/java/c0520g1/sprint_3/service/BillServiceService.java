package c0520g1.sprint_3.service;

import c0520g1.sprint_3.model.BillServices;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BillServiceService {

    List<BillServices> findAll();

    void create(BillServices billServices);

    void deleteById(Long id);

    BillServices findById(Long id);
}
