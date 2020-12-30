package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.model.Services;
import c0520g1.sprint_3.model.User;
import c0520g1.sprint_3.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/request")
public class RequestServiceController {
    @Autowired
    private ServicesService services;

    @GetMapping(value = "/service")
    public ResponseEntity<List<Services>> getListService() {
        List<Services> servicesList = services.findAll();
        if (servicesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(servicesList, HttpStatus.OK);
    }
}
