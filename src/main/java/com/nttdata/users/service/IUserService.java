package com.nttdata.users.service;

import com.nttdata.users.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<UserEntity> findAll();

    UserEntity saveUser(UserEntity user);

    UserEntity findById(String id);

    UserEntity update(String id, UserEntity old);

    UserEntity delete(String id);
}
