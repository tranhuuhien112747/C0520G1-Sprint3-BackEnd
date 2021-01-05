package c0520g1.sprint_3.service.impl;

import c0520g1.sprint_3.model.Bill;
import c0520g1.sprint_3.repository.BillRepository;
import c0520g1.sprint_3.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public void create(Bill bill) {
        billRepository.save(bill);

    }

    @Override
    public void deleteById(Long id) {
        billRepository.deleteById(id);

    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElse(null);
    }
}
