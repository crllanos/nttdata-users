package com.nttdata.users.service.impl

import com.nttdata.users.entity.UserEntity
import com.nttdata.users.repository.UserRepository
import com.nttdata.users.util.Util
import org.spockframework.spring.SpringBean
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class UserServiceITSpec extends Specification {
    @SpringBean
    UserRepository userRepository = Mock()

    @SpringBean
    Util util = Mock()

    @Subject
    UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, util)

    def "assert saveUser ERROR: Invalid password"(){
        given:
            def user = UserEntity.builder()
                .name("dummy")
                .email("dummy@test.com")
                .password("noTvalid")
                .build();
        when:
            def result = userServiceImpl.saveUser(user)

        then:
            def exception = thrown(IllegalArgumentException)
            assert exception.message == "Invalid password"
    }



    def "assert saveUser ERROR: Invalid e-mail"(){
        given:
            def user = UserEntity.builder()
                .name("dummy")
                .email("dummy_test.com")
                .password("S1Valid4")
                .build();
        when:
            def result = userServiceImpl.saveUser(user)

        then:
            def exception = thrown(IllegalArgumentException)
            assert exception.message == "Invalid e-mail"
    }



    def "assert saveUser ok"(){
        given:
            def name = "dummy"
            def user = UserEntity.builder()
                .name(name)
                .email("dummy@test.com")
                .password("S1Valid4")
                .build()
            userRepository.save() >> UserEntity.builder().name(name).build()

        when:
            def result = userServiceImpl.saveUser(user)
        then:
            assert user.getName() == name
    }
}
