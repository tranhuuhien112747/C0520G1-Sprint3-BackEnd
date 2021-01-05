package c0520g1.sprint_3.service.impl;

import c0520g1.sprint_3.model.Bill;
import c0520g1.sprint_3.model.StatusComputer;
import c0520g1.sprint_3.repository.StatusComputerRepository;
import c0520g1.sprint_3.service.StatusComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusComputerServiceImpl implements StatusComputerService {
    @Autowired
    StatusComputerRepository statusComputerRepository;
    @Override
    public List<StatusComputer> findAll() {
        return statusComputerRepository.findAll();
    }

    @Override
    public StatusComputer findById(Long id) {
        return statusComputerRepository.findById(id).orElse(null);
    }

    @Override
    public StatusComputer findByName(String name) {
        return statusComputerRepository.findByStatusComputerName(name);
    }
}
