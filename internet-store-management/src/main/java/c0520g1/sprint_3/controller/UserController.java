package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.dto.ImageDTO;
import c0520g1.sprint_3.model.User;
import c0520g1.sprint_3.service.RoleService;
import c0520g1.sprint_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    //    @Autowired
//    PasswordEncoder passwordEncoder;

    @GetMapping("")
    public ResponseEntity<List<User>> getListUser() {
        List<User> userList = userService.findAllByStatusTrue();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }

    @GetMapping("/getUser/{idUser}")
    public ResponseEntity<User> getUser(@PathVariable Long idUser) {
        User user = userService.findById(idUser);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/edit/{idUser}")
    public ResponseEntity<Void> editUser(@PathVariable Long idUser, @RequestBody User user) {
        User userNew = userService.findById(idUser);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userNew.setUsername(user.getUsername());
            userNew.setFullName(user.getFullName());
//            userNew.setPassword(passwordEncoder.encode(user.getPassword()));
            userNew.setPassword(user.getPassword());
            userNew.setGender(user.getGender());
            userNew.setImage(user.getImage());
            userNew.setEmail(user.getEmail());
            userNew.setMoney(user.getMoney());
            userNew.setBirthday(user.getBirthday());
            userService.save(userNew);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}/change-image")
    public ResponseEntity<Void> updateImage(@PathVariable long id, @RequestBody ImageDTO imageDTO) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            user.setImage(imageDTO.getImage());
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        User user = userService.findById(idUser);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@Valid @RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            user.setRole(roleService.findById((long) 2));
            user.setImage("");
            user.setTimeRemaining("");
            user.setStatus(true);
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPassword(user.getPassword());
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
