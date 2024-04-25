package com.nttdata.users.service.impl;

import com.nttdata.users.entity.RoleEntity;
import com.nttdata.users.enums.RoleEnum;
import com.nttdata.users.repository.RoleRepository;
import com.nttdata.users.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("UserServiceImpl")
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<RoleEntity> findByName(RoleEnum role) {
        return roleRepository.findByName(role);
    }
}
