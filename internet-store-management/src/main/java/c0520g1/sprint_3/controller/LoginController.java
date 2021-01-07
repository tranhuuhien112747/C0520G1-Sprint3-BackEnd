package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.config.JwtTokenProvider;
import c0520g1.sprint_3.dto.TokenDTO;
import c0520g1.sprint_3.dto.UserDTO;
import c0520g1.sprint_3.model.*;
import c0520g1.sprint_3.repository.UserRepository;
import c0520g1.sprint_3.service.BillService;
import c0520g1.sprint_3.service.RoleService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
  public static String GOOGLE_CLIENT_ID = "103585693874-0bjkl21cmmjf8d9n09io95ciuveiievl.apps.googleusercontent.com";

  public static String PASSWORD = "123";
  private static String emailInput;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleService roleService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  JavaMailSender javaMailSender;

  @Autowired
  private BillService bill;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtTokenProvider.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(new UserDTO(jwt,
            userDetails.getIdUser(),
            userDetails.getUsername(),
            userDetails.getFullName(),
            roles,
            userDetails.getMoney(),
            userDetails.getTimeRemaining()
    ));
  }

  @PostMapping("login-google")
  public ResponseEntity<?> authenticateByGoogleAccount(@RequestBody TokenDTO tokenDTO) throws IOException {
    final NetHttpTransport transport = new NetHttpTransport();
    final JacksonFactory jacksonFactory = new JacksonFactory();
    GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID));
    final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDTO.getValue());
    final GoogleIdToken.Payload payload = googleIdToken.getPayload();
    User user;
    String userName;
    userName = (String) payload.get("name");
    if (userRepository.existsByUsername(userName)) {
      user = userRepository.findUserByUsername(userName);
    } else {
      user = saveUser(userName);
    }
    UserDTO userDTO = login(user);
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  @PostMapping("login-facebook")
  public ResponseEntity<?> authenticateByFacebookAccount(@RequestBody TokenDTO tokenDTO) throws IOException {
    Facebook facebook = new FacebookTemplate(tokenDTO.getValue());
    final String[] fields = {"name", "picture"};
    org.springframework.social.facebook.api.User userFacebook = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
    User user;
    System.out.println(userFacebook);
    if (userFacebook.getName() == null) {
      user = saveUser(userFacebook.getName());
    } else {
      if (userRepository.existsByUsername(userFacebook.getName())) {
        user = userRepository.findUserByUsername(userFacebook.getName());
      } else {
        user = saveUser(userFacebook.getName());
      }
    }
    UserDTO userDTO = login(user);
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  private UserDTO login(User user) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(user.getUsername(), PASSWORD)
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtTokenProvider.generateJwtToken(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());
    return new UserDTO(jwt,
            userDetails.getIdUser(),
            userDetails.getUsername(),
            userDetails.getFullName(),
            roles,
            userDetails.getMoney(),
            userDetails.getTimeRemaining());
  }

  private User saveUser(String value) {
    User user = new User();
    user.setUsername(value);
    user.setEmail(value);
    user.setPassword(passwordEncoder.encode(PASSWORD));
    user.setFullName("N/A");
    Role rolUser = roleService.findById(2L);
    user.setRole(rolUser);
    return userRepository.save(user);
  }

  @GetMapping("/send")
  public ResponseEntity<String> sendEmail(@RequestParam("to") String to){
    User user = userRepository.findUserByEmail(to);
    if(user != null){
      SimpleMailMessage msg = new SimpleMailMessage();
      msg.setTo(to);
      emailInput = to;
      msg.setSubject("Mã xác nhận đặt lại mật khẩu.");
      int randomCode = ((int) Math.floor(Math.random() * 8999) + 10000);
      msg.setText("Mã xác nhận của bạn là: "+ randomCode);
      javaMailSender.send(msg);
      return new ResponseEntity<>(randomCode+"",HttpStatus.OK);
    }
    return new ResponseEntity<>( null,HttpStatus.OK);
  }
  @GetMapping("/resetPassWord")
  public ResponseEntity<Boolean> resetPassWord(@RequestParam("password") String password){
     User user = userRepository.findUserByEmail(emailInput);
      if(user == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      user.setPassword(passwordEncoder.encode(password));
      userRepository.save(user);
      return new ResponseEntity<>(true,HttpStatus.OK);
  }
  @GetMapping("/find-by/{id}")
  public ResponseEntity<User> findByUser(@PathVariable("id") String userName){
    User user = userRepository.findUserByUsername(userName);
    if(user == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user,HttpStatus.OK);
  }
  @GetMapping("/save-user")
  public ResponseEntity<Void> saveTimeRemaining(@RequestParam("userName") String userName, @RequestParam("time") String time){
    User user = userRepository.findUserByUsername(userName);
    if(user == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    user.setTimeRemaining(time);
    userRepository.save(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/get-message")
  public ResponseEntity<Boolean> getMessage(){
    List<Bill> billList = bill.findBillByStatusDisplayTrue();
    if (billList.isEmpty()) {
      return new ResponseEntity<>(false,HttpStatus.OK);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
