package com.web_project.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private UserRoleDto role;
}
