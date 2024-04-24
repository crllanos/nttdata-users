package com.nttdata.users.service;

import com.nttdata.users.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> findAll();

    UserEntity saveUser(UserEntity user);

    UserEntity findById(String id);
}
