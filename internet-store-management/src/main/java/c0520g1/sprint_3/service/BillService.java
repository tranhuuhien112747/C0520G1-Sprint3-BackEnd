package c0520g1.sprint_3.service;

import c0520g1.sprint_3.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> findAll();

    void create(Bill bill);

    void deleteById(Long id);

    Bill findById(Long id);
}
