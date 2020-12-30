package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.model.Computer;
import c0520g1.sprint_3.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/test")
public class HelloController {
    @Autowired
    ComputerService computerService;
    @GetMapping("/hello")
    public ResponseEntity<List<Computer>> abc(){
        return new ResponseEntity<>(computerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by")
    public ResponseEntity<String> bcd(){
        return new ResponseEntity<>("by", HttpStatus.OK);
    }
}
