package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.dto.ComputerDTO;
import c0520g1.sprint_3.model.Computer;
import c0520g1.sprint_3.model.StatusComputer;
import c0520g1.sprint_3.service.ComputerService;
import c0520g1.sprint_3.service.StatusComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/computer")

public class ComputerController {
    @Autowired
    ComputerService computerService;
    @Autowired
    StatusComputerService statusComputerService;
    private BindingResult bindingResult;

    @GetMapping("/list")
    public ResponseEntity<List<ComputerDTO>> listComputers() {
        List<Computer> computers = computerService.findAll();
        List<ComputerDTO> computerDTOS = new ArrayList<>();
        if (computers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Computer computer: computers){
                ComputerDTO computerDTO = new ComputerDTO();
                computerDTO.setIdComputer(computer.getIdComputer());
                computerDTO.setComputerName(computer.getComputerName());
                computerDTO.setTimeStart(computer.getTimeStart());
                computerDTO.setTimeUser(computer.getTimeUser());
                computerDTO.setIdStatusComputer(computer.getStatusComputer().getIdStatusComputer());
                computerDTO.setStatusComputerName(computer.getStatusComputer().getStatusComputerName());
                computerDTO.setFullName(computer.getUser().getFullName());
                computerDTO.setIdUser(computer.getUser().getIdUser());
                computerDTO.setMoney(computer.getUser().getMoney());
                computerDTOS.add(computerDTO);
            }
            return new ResponseEntity<>(computerDTOS, HttpStatus.OK);
        }
    }

    @GetMapping("/list/statusComputer")
    public ResponseEntity<List<StatusComputer>> listStatusComputers() {
        List<StatusComputer> statusComputers = statusComputerService.findAll();
        if (statusComputers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(statusComputers, HttpStatus.OK);
        }
    }

    @GetMapping("/{idComputer}")
    public ResponseEntity<Computer> getComputer(@PathVariable Long idComputer) {
        Computer computer = computerService.findById(idComputer);
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }
    @GetMapping("/search/{value}")
    public ResponseEntity<List<ComputerDTO>> getUser(@PathVariable Long value) {
        List<Computer> computers = computerService.findAllByStatusComputerName(value);
        List<ComputerDTO> computerDTOS = new ArrayList<>();
        if (computers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Computer computer: computers){
                ComputerDTO computerDTO = new ComputerDTO();
                computerDTO.setIdComputer(computer.getIdComputer());
                computerDTO.setComputerName(computer.getComputerName());
                computerDTO.setTimeStart(computer.getTimeStart());
                computerDTO.setTimeUser(computer.getTimeUser());
                computerDTO.setIdStatusComputer(computer.getStatusComputer().getIdStatusComputer());
                computerDTO.setStatusComputerName(computer.getStatusComputer().getStatusComputerName());
                computerDTO.setFullName(computer.getUser().getFullName());
                computerDTO.setIdUser(computer.getUser().getIdUser());
                computerDTO.setMoney(computer.getUser().getMoney());
                computerDTOS.add(computerDTO);
            }
            return new ResponseEntity<>(computerDTOS, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Computer> createUser(@RequestBody ComputerDTO computer) {
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Computer computerNew = new Computer();
        computerNew.setComputerName(computer.getComputerName());
        computerNew.setStatus(true);
//        computerNew.setTimeStart(String.valueOf(LocalTime.now()));
        computerNew.setStatusComputer(statusComputerService.findById(computer.getIdStatusComputer()));
        computerService.create(computerNew);
        Computer computer1;
        computer1 = computerService.findByComputerName(computer.getComputerName());
        return new ResponseEntity<>(computer1, HttpStatus.OK);
    }

    @PutMapping("/edit/{idComputer}")
    public ResponseEntity<Void> editUser(@PathVariable Long idComputer, @RequestBody ComputerDTO computer) {
        Computer computerNew = computerService.findById(idComputer);
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            computerNew.setComputerName(computer.getComputerName());
            computerNew.setStatusComputer(statusComputerService.findById(computer.getIdStatusComputer()));
            computerService.create(computerNew);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{idComputer}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idComputer) {
        Computer user = computerService.findById(idComputer);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            computerService.deleteById(idComputer);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}