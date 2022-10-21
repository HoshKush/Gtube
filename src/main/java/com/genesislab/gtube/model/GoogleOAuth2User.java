package com.genesislab.gtube.model;

import com.genesislab.gtube.entity.Role;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Builder
@AllArgsConstructor
public class GoogleOAuth2User implements OAuth2User {

    Map<String, Object> attributes;
    Role role;

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role == null ? null : Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    public String getOAuthId() {
        return (String) attributes.get("sub");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }
}
