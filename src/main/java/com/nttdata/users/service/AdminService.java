package com.nttdata.users.service;

import com.nttdata.users.dto.AdminDTO;
import com.nttdata.users.entity.AdminEntity;

import java.util.Optional;

public interface AdminService {
    AdminEntity saveAdmin(AdminEntity admin);

    AdminEntity createAdministrator(AdminDTO admin);

    Optional<AdminEntity> findByEmail(String email);
}
