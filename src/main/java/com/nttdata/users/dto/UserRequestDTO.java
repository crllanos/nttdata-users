package com.nttdata.users.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String name;
    private String email;
    transient private String password;
    private List<PhoneDTO> phones;
}
