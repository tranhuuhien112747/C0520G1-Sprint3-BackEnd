package c0520g1.sprint_3.service;

import c0520g1.sprint_3.model.Bill;
import c0520g1.sprint_3.model.Computer;
import c0520g1.sprint_3.model.StatusComputer;

import java.util.List;

public interface StatusComputerService {
    List<StatusComputer> findAll();
    StatusComputer findById(Long id);
    StatusComputer findByName(String name);
}
