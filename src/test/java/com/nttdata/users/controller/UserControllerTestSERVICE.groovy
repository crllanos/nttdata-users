package com.nttdata.users.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class UserControllerTestSERVICE extends Specification {

    @Autowired
    private UserController userController;

    def "assert bean creation"(){
        expect: "bean crwation ok"
        userController != null;
    }
}
