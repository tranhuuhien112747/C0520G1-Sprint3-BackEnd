package c0520g1.sprint_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> abc(){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @GetMapping("/by")
    public ResponseEntity<String> bcd(){
        return new ResponseEntity<>("by", HttpStatus.OK);
    }
}
