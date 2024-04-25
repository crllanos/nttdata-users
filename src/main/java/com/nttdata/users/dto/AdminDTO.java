package com.nttdata.users.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private String email;
    private String password;
    private String name;
    private String role;
}
