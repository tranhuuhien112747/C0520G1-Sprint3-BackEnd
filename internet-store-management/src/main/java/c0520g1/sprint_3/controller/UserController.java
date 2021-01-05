package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.dto.ChangePasswordDTO;
import c0520g1.sprint_3.model.User;
import c0520g1.sprint_3.service.RoleService;
import c0520g1.sprint_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController
 * <p>
 * Version 1.0
 * <p>
 * Date: 07-12-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR
 * ----------------------------
 * 28-12-2020         TungTS
 */

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @Autowired
    PasswordEncoder passwordEncoder;


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
            userNew.setBirthday(user.getBirthday());
            userNew.setGender(user.getGender());
            userNew.setEmail(user.getEmail());
            userService.save(userNew);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/change-image")
    public ResponseEntity<Void> updateImage(@RequestParam("idUser") Long idUser, @RequestParam("image") String image){
        User user = userService.findById(idUser);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            user.setImage(image);
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
    public ResponseEntity<Void> addNewUser(@Valid @RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            user.setRole(roleService.findById((long) 2));
            if (user.getGender()) {
                user.setImage("https://st.quantrimang.com/photos/image/2017/04/08/anh-dai-dien-FB-200.jpg");
            } else {
                user.setImage("https://scr.vn/wp-content/uploads/2020/07/%E1%BA%A2nh-%C4%91%E1%BA%A1i-di%E1%BB%87n-FB-m%E1%BA%B7c-%C4%91%E1%BB%8Bnh-n%E1%BB%AF-768x768.jpg");
            }
            user.setTimeRemaining("");
            user.setStatus(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping("/change-password/{idUser}")
    public ResponseEntity<Void> changePassWordUser(@PathVariable Long idUser, @RequestBody ChangePasswordDTO changePasswordDTO) {
        User userNew = userService.findById(idUser);
        if (userNew == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userNew.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            userService.save(userNew);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/inputSearch")
    public ResponseEntity<List<User>> searchUsers(@RequestParam("valueSearch") String inputSearch) {
        List<User> userList = userService.searchUser(inputSearch);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
