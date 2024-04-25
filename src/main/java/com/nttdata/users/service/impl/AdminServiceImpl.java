package com.nttdata.users.service.impl;

import com.nttdata.users.dto.AdminDTO;
import com.nttdata.users.entity.AdminEntity;
import com.nttdata.users.entity.RoleEntity;
import com.nttdata.users.enums.RoleEnum;
import com.nttdata.users.repository.AdminRepository;
import com.nttdata.users.repository.RoleRepository;
import com.nttdata.users.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public AdminEntity saveAdmin(AdminEntity admin) {
        return adminRepository.save(admin);
    }

    public AdminEntity createAdministrator(AdminDTO admin) {
        Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        if (optionalRole.isEmpty()) {
            return null;
        }
        return adminRepository.save(AdminEntity.builder()
                .name(admin.getName())
                .email(admin.getEmail())
                .password(passwordEncoder.encode(admin.getPassword()))
                .role(optionalRole.get())
                .build());
    }

    @Override
    public Optional<AdminEntity> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

}
