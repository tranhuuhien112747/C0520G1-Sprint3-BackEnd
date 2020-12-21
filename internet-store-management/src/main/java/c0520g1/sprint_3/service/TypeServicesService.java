package c0520g1.sprint_3.service;

import c0520g1.sprint_3.model.TypeServices;

import java.util.List;

public interface TypeServicesService {

    List<TypeServices> findAll();

    TypeServices findById(Long id);

    void save(TypeServices typeServices);

    void deleteById(Long id);
}
