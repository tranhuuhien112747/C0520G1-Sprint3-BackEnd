package c0520g1.sprint_3.service;
import c0520g1.sprint_3.model.Services;

import java.util.List;

public interface ServicesService {

    List<Services> findAll();

    Services findById(Long id);

    void save(Services services);

    void deleteById(Long id);

    Services findServiceByName(String name);
}
