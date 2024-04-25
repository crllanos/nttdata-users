package com.nttdata.users.service.impl;

import com.nttdata.users.entity.AdminEntity;
import com.nttdata.users.repository.AdminRepository;
import com.nttdata.users.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminEntity saveAdmin(AdminEntity admin) {
        return adminRepository.save(admin);
    }
}
