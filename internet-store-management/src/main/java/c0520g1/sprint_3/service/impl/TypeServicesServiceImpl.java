package c0520g1.sprint_3.service.impl;

import c0520g1.sprint_3.model.TypeServices;
import c0520g1.sprint_3.repository.TypeServicesRepository;
import c0520g1.sprint_3.service.TypeServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServicesServiceImpl implements TypeServicesService {

    @Autowired
    TypeServicesRepository typeServicesRepository;
    
    @Override
    public List<TypeServices> findAll() {
        return typeServicesRepository.findAll();
    }

    @Override
    public TypeServices findById(Long id) {
        return typeServicesRepository.findById(id).orElse(null);
    }

    @Override
    public void save(TypeServices typeServices) {
        typeServicesRepository.save(typeServices);
    }

    @Override
    public void deleteById(Long id) {
        typeServicesRepository.deleteById(id);
    }
}
