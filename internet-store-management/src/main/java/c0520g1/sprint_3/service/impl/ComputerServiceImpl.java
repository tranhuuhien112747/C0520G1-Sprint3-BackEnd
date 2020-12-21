package c0520g1.sprint_3.service.impl;

import c0520g1.sprint_3.model.Bill;
import c0520g1.sprint_3.model.Computer;
import c0520g1.sprint_3.repository.ComputerRepository;
import c0520g1.sprint_3.service.ComputerService;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    @Override
    public void create(Computer computer) {
        computerRepository.save(computer);

    }

    @Override
    public void deleteById(Long id) {
        computerRepository.deleteById(id);

    }

    @Override
    public Computer findById(Long id) {
        return computerRepository.findById(id).orElse(null);
    }
}
