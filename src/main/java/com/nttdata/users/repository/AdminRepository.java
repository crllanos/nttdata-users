package com.nttdata.users.repository;

import com.nttdata.users.entity.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository  extends CrudRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByEmail(String email);
}
