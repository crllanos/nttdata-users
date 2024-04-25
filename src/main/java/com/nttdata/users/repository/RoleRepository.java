package com.nttdata.users.repository;

import com.nttdata.users.entity.RoleEntity;
import com.nttdata.users.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(RoleEnum name);
}