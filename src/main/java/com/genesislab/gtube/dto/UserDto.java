package com.genesislab.gtube.dto;

import com.genesislab.gtube.entity.Role;
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
    private String role;

    public Role getRoleAsEnum() {
        return Role.valueOf(role);
    }
}
