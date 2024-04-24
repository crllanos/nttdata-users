package com.nttdata.users.controller;

import com.nttdata.users.dto.AdminDTO;
import com.nttdata.users.dto.LoginRequestDTO;
import com.nttdata.users.dto.LoginResponseDTO;
import com.nttdata.users.entity.AdminEntity;
import com.nttdata.users.service.AuthenticationService;
import com.nttdata.users.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AdminEntity> register(@RequestBody AdminDTO registerUserDto) {
        AdminEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginRequestDTO login) {
        AdminEntity authenticatedUser = authenticationService.authenticate(login);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return ResponseEntity.ok(LoginResponseDTO.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build());
    }
}
