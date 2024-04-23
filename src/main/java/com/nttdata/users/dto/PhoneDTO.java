package com.nttdata.users.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    private Integer number;
    private Integer citycode;
    private Integer contrycode;
}
