package com.nttdata.users.service;

import com.nttdata.users.dto.AdminDTO;
import com.nttdata.users.dto.LoginRequestDTO;
import com.nttdata.users.entity.AdminEntity;
import com.nttdata.users.entity.RoleEntity;
import com.nttdata.users.enums.RoleEnum;
import com.nttdata.users.repository.AdminRepository;
import com.nttdata.users.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AdminEntity signup(AdminDTO input) {
        Optional<RoleEntity> role = roleRepository.findByName(RoleEnum.valueOf(input.getRole()));
        if(!role.isPresent())
            throw new IllegalArgumentException("Role doesn't exists.");

        AdminEntity admin = AdminEntity.builder()
                        .name(input.getName())
                        .email(input.getEmail())
                        .password(passwordEncoder.encode(input.getPassword())) // @todo validar formato password
                        .role(role.get())
                .build();
        try{
            return adminRepository.save(admin);
        }catch (Exception e){
            throw new IllegalArgumentException("Admin already exists!");
        }
    }

    public AdminEntity authenticate(LoginRequestDTO input) {
        Authentication a = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );
        return adminRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Admin %s not found", input.getEmail())));
    }

    public List<AdminEntity> allUsers() {
        List<AdminEntity> users = new ArrayList<>();
        adminRepository.findAll().forEach(users::add);
        return users;
    }
}
