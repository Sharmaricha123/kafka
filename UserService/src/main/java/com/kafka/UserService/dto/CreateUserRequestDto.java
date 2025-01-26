package com.kafka.UserService.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {

    private Long id;
    private String name;
    private String email;
}
