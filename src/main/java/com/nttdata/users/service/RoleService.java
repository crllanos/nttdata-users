package com.nttdata.users.service;

import com.nttdata.users.entity.RoleEntity;
import com.nttdata.users.enums.RoleEnum;

import java.util.Optional;

public interface RoleService {
    RoleEntity saveRole(RoleEntity role);

    Optional<RoleEntity> findByName(RoleEnum admin);
}
