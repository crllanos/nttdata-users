package com.nttdata.users.controller

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@WebMvcTest
class UserControllerTest extends Specification {
    @Autowired
    private MockMvc mvcController;

    // https://youtu.be/3hs3VUXWv48?t=422
    def "hello hello"(){
        expect:
        mvcController.perform(MockMvcRequestBuilders.get("/user-registry/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }
}
