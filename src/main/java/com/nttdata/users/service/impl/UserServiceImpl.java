package com.nttdata.users.service.impl;

import com.nttdata.users.entity.UserEntity;
import com.nttdata.users.repository.UserRepository;
import com.nttdata.users.service.IUserService;
import com.nttdata.users.util.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final Util util;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");

    @Override
    public List<UserEntity> findAll() {
        log.info("UserServiceImpl.findAll()");
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info(String.format("UserService.saveUser() : %s", util.obj2Json(user)));

        isValidUser(user, true);

        user.setId(UUID.randomUUID());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken(user.getId().toString()); // @todo Cambiar por jwt
        // para efectos del ejemplo, el usuario quedará activo de manera aleatoria
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
        user.setActive(randomNum %2 == 0);

        log.info(String.format("Persisting: %s", util.obj2Json(user)));
        return userRepository.save(user);
    }


    /***
     * Validators
     */
    private void isValidUser(UserEntity user, boolean isNewEmail){
        // Validates password
        if(ObjectUtils.isEmpty(user.getPassword())  || !validatePassword(user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }

        // validates email
        if(ObjectUtils.isEmpty(user.getEmail()) || !validateEmail(user.getEmail())){
            throw new IllegalArgumentException("Invalid e-mail");
        }

        if(isNewEmail){
            // validate email unique
            if(!ObjectUtils.isEmpty(userRepository.findByEmail(user.getEmail()))){
                throw new IllegalArgumentException("Email already exists");
            }
        }
    }


    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private static boolean validatePassword(String passw) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passw);
        return matcher.find();
    }
}
