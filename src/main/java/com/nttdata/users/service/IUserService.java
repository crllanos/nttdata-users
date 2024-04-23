package com.nttdata.users.service;

import com.nttdata.users.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> findAll();
}
