package com.genesislab.gtube.model.profile;

import com.genesislab.gtube.entity.Role;
import com.genesislab.gtube.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserProfile {
    private final String oauthId;
    private final String name;
    private final String email;
    public User toUser() {
        return User.builder()
                .oauthId(this.oauthId)
                .email(this.email)
                .name(this.name)
                .role(Role.USER)
                .build();
    }
}
