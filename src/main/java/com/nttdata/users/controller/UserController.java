package com.nttdata.users.controller;

import com.nttdata.users.dto.UserResponseDTO;
import com.nttdata.users.entity.UserEntity;
import com.nttdata.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-registry/")
public class UserController {

    private final IUserService iUserService;

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
}
