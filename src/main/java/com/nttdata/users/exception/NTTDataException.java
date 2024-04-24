package com.nttdata.users.exception;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NTTDataException {
    private String message;
}
