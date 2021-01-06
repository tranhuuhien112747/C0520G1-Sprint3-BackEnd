package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.model.Services;
import c0520g1.sprint_3.service.ServicesService;
import c0520g1.sprint_3.service.TypeServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServicesService servicesService;
    @Autowired
    public TypeServicesService typeServicesService;

    @GetMapping()
    public ResponseEntity<List<Services>> showServiceList() {
        List<Services> servicesList = servicesService.findAll();
        if (servicesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(servicesList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Services> createService(@RequestBody Services services) {
        Services services1;
        if (services == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if((services.getUnit().equals("dĩa"))||(services.getUnit().equals("tô"))){
            services.setTypeServices(typeServicesService.findById(2L));
        } else if ((services.getUnit().equals("chai"))||(services.getUnit().equals("ly"))||(services.getUnit().equals("lon"))) {
            services.setTypeServices(typeServicesService.findById(1L));
        } else if (services.getUnit().equals("thẻ")){
            services.setTypeServices(typeServicesService.findById(3L));
        } else {
            services.setTypeServices(typeServicesService.findById(4L));
        }
        servicesService.save(services);
        services1 = servicesService.findServiceByName(services.getServiceName());
        return new ResponseEntity<>(services1, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> updateService(@PathVariable long id, @RequestBody Services services) {
        Services services1 = servicesService.findById(id);
        if (services1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        services1.setServiceName(services.getServiceName());
        services1.setPrice(services.getPrice());
        services1.setQuantity(services.getQuantity());
        services1.setUnit(services.getUnit());
        servicesService.save(services1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable long id) {
        Services services = servicesService.findById(id);
        if (services == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        servicesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<Services> findServiceById(@PathVariable long id) {
        Services services = servicesService.findById(id);
        if (services == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
}
