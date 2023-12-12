package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.ERole;
import com.msprcrm.msprcrm.entity.Role;
import com.msprcrm.msprcrm.entity.User;
import com.msprcrm.msprcrm.payload.CreateUserRequest;
import com.example.mspr.payload.JwtResponse;
import com.msprcrm.msprcrm.repository.RoleRepository;
import com.msprcrm.msprcrm.repository.UserRepository;
import com.msprcrm.msprcrm.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest createUserRequest){
        try {
            User user = new User();
            user.setUsername(createUserRequest.getUsername());
            user.setPassword(encoder.encode(createUserRequest.getPassword()));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.User)
                    .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("login")
    public ResponseEntity<?>login(@Valid @RequestBody CreateUserRequest createUserRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(createUserRequest.getUsername(), createUserRequest.getPassword()));

            Optional<User> optUser = userRepository.findUserByUsername(createUserRequest.getUsername());
            if (optUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("L'utilisateur n'existe pas");
            }

            User user = optUser.get();
            if (!user.getUsername().equals(createUserRequest.getUsername())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("L'utilisateur n'existe pas");
            }

            if (authentication.isAuthenticated()) {
                String token = jwtUtils.generateToken(createUserRequest.getUsername());

                JwtResponse jwtResponse = new JwtResponse();
                jwtResponse.setToken(token);
                jwtResponse.setUsername(user.getUsername());

                return ResponseEntity.ok().body(jwtResponse);

            } else {
                throw new UsernameNotFoundException("L'utilisateur n'existe pas");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
