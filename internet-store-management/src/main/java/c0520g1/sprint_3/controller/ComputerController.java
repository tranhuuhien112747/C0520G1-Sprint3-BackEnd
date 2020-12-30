package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.model.Computer;
import c0520g1.sprint_3.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("computer")
@CrossOrigin
public class ComputerController {
    @Autowired
    ComputerService computerService;

    @GetMapping("/list")
    public ResponseEntity<List<Computer>> listComputers() {
        List<Computer> computers = computerService.findAll();
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @GetMapping("/{idComputer}")
    public ResponseEntity<Computer> getUser(@PathVariable Long idComputer) {
        Computer computer = computerService.findById(idComputer);
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createUser(@RequestBody Computer computer) {
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            computerService.create(computer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PutMapping("/edit/{idComputer}")
    public ResponseEntity<Void> editUser(@PathVariable Long idComputer, @RequestBody Computer computer) {
        Computer computerNew = computerService.findById(idComputer);
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            computerService.create(computer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @DeleteMapping("/delete/{idComputer}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idComputer) {
        Computer user = computerService.findById(idComputer);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computerService.deleteById(idComputer);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
