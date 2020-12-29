package c0520g1.sprint_3.controller;

import c0520g1.sprint_3.config.JwtTokenProvider;
import c0520g1.sprint_3.dto.TokenDTO;
import c0520g1.sprint_3.dto.UserDTO;
import c0520g1.sprint_3.model.LoginRequest;
import c0520g1.sprint_3.model.Role;
import c0520g1.sprint_3.model.User;
import c0520g1.sprint_3.model.UserDetailsImpl;
import c0520g1.sprint_3.repository.UserRepository;
import c0520g1.sprint_3.service.RoleService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                roles));
    }

    @PostMapping("login-google")
    public ResponseEntity<?> authenticateByGoogleAccount(@RequestBody TokenDTO tokenDTO) throws IOException {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDTO.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        User user;
        if (userRepository.existsByUsername(payload.getEmail())) {
            user = userRepository.findUserByUsername(payload.getEmail());
        } else {
            user = saveUser(payload.getEmail());
        }
        UserDTO userDTO = login(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("login-facebook")
    public ResponseEntity<?> authenticateByFacebookAccount(@RequestBody TokenDTO tokenDTO) throws IOException {
        Facebook facebook = new FacebookTemplate(tokenDTO.getValue());
        final String[] fields = {"username", "picture"};
        org.springframework.social.facebook.api.User userFacebook = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        User user;
        if (userFacebook.getName() == null) {
            user = saveUser(userFacebook.getName());
        } else {
            if (userRepository.existsByUsername(userFacebook.getEmail())) {
                user = userRepository.findUserByUsername(userFacebook.getEmail());
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
                roles);
    }

    private User saveUser(String email) {
        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(PASSWORD));
        user.setFullName("N/A");
        Role rolUser = roleService.findById(2L);
        user.setRole(rolUser);
        return userRepository.save(user);
    }
}
