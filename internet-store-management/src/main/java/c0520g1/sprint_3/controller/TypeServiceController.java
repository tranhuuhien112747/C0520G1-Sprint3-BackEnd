package c0520g1.sprint_3.controller;
import c0520g1.sprint_3.model.TypeServices;
import c0520g1.sprint_3.service.TypeServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/typeService")
public class TypeServiceController {
    @Autowired
    public TypeServicesService typeServicesService;
    @GetMapping()
    public ResponseEntity<List<TypeServices>> showTypeServiceList() {
        List<TypeServices> typeServicesList = typeServicesService.findAll();
        if (typeServicesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeServicesList, HttpStatus.OK);
    }

}
