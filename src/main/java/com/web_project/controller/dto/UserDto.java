package com.web_project.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private UserRoleDto role;
}
