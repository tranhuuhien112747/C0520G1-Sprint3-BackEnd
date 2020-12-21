package c0520g1.sprint_3.service.impl;

import c0520g1.sprint_3.model.Services;
import c0520g1.sprint_3.repository.ServicesRepository;
import c0520g1.sprint_3.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServicesService {

    @Autowired
    ServicesRepository servicesRepository;


    @Override
    public List<Services> findAll() {
        return servicesRepository.findAll();
    }

    @Override
    public Services findById(Long id) {
        return servicesRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Services services) {
        servicesRepository.save(services);
    }

    @Override
    public void deleteById(Long id) {
        servicesRepository.deleteById(id);
    }
}
