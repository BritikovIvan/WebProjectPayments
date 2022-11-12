package com.webproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private UserRoleDto role;
}
