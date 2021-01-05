package c0520g1.sprint_3.service.impl;


import c0520g1.sprint_3.model.BillServices;
import c0520g1.sprint_3.repository.BillServiceRepository;
import c0520g1.sprint_3.service.BillServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServicesServiceImpl implements BillServiceService {
    @Autowired
    private BillServiceRepository billServiceRepository;

    @Override
    public List<BillServices> findAll() {
        return billServiceRepository.findAll();
    }

    @Override
    public void create(BillServices billServices) {
        billServiceRepository.save(billServices);

    }

    @Override
    public void deleteById(Long id) {
        billServiceRepository.deleteById(id);

    }

    @Override
    public BillServices findById(Long id) {
        return billServiceRepository.findById(id).orElse(null);
    }
}
