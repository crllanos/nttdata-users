package com.nttdata.users.controller;

import com.nttdata.users.dto.PhoneDTO;
import com.nttdata.users.dto.UserRequestDTO;
import com.nttdata.users.dto.UserResponseDTO;
import com.nttdata.users.entity.PhoneEntity;
import com.nttdata.users.entity.UserEntity;
import com.nttdata.users.service.IUserService;
import com.nttdata.users.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-registry/")
public class UserController {

    private final IUserService iUserService;

    private final Util util;

    @GetMapping
    public List<UserResponseDTO> getUserList(){
        log.info("GET /user-registry/");
        List<UserResponseDTO> response = new ArrayList<>();
        for(UserEntity u : iUserService.findAll()){
            response.add(userEntity2DTO(u));
        }
        log.info(String.format("response: %d", response.size()));
        return response;
    }


    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequest){
        log.info("POST /user-registry/" + util.obj2Json(userRequest));
        log.info(String.format("Creating user: %s", util.obj2Json(userRequest)));
        UserEntity saved = iUserService.saveUser(userRequest2Entity(userRequest));
        log.info(String.format("response: %s", util.obj2Json(saved)));
        return userEntity2DTO(saved);
    }


    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable String id){
        log.info(String.format("GET /user-registry/%s", id));
        UserEntity found = iUserService.findById(id);
        log.info(String.format("response: %s", util.obj2Json(found)));
        return userEntity2DTO(found);
    }


    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable String id, @RequestBody UserRequestDTO userRequest){
        log.info(String.format("PUT /user-registry/%s", id));
        log.info(String.format("Updating user: %s", util.obj2Json(userRequest)));
        UserEntity updated = iUserService.update(id, userRequest2Entity(userRequest));
        log.info(String.format("response: %s", util.obj2Json(updated)));
        return userEntity2DTO(updated);
    }


    /**
     * User entity to DTO
     *
     */
    private UserResponseDTO userEntity2DTO(UserEntity entity){
        return UserResponseDTO.builder()
                .id(entity.getId().toString())
                .name(entity.getName())
                .email(entity.getEmail())
                .created(entity.getCreated())
                .modified(entity.getModified())
                .lastLogin(entity.getLastLogin())
                .token(entity.getToken())
                .active(entity.isActive())
                .build();
    }

    /**
     * User DTO to entity
     *
     */
    private UserEntity userRequest2Entity(UserRequestDTO userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(phoneRequest2Entity(userRequest))
                .build() ;
    }

    /**
     * Phones DTO to entity
     *
     */
    private List<PhoneEntity> phoneRequest2Entity(UserRequestDTO userRequest){
        List<PhoneEntity> phones = new ArrayList<>();
        for(PhoneDTO p : userRequest.getPhones()){
            phones.add(PhoneEntity.builder()
                    .contrycode(p.getContrycode())
                    .citycode(p.getCitycode())
                    .number(p.getNumber())
                    .build());
        }
        return phones;
    }
}
