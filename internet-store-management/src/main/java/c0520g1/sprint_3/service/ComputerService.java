package c0520g1.sprint_3.service;


import c0520g1.sprint_3.model.Computer;

import java.util.List;

public interface ComputerService {
    List<Computer> findAll();

    void create(Computer computer);

    void deleteById(Long id);

    Computer findById(Long id);
}
