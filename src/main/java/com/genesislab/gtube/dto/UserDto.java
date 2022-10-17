package com.genesislab.gtube.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String password;
    private String email;
    private String name;
    private String phone;
    private Set<String> roles;
}
