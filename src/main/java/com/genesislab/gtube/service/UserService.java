package com.genesislab.gtube.service;

import com.genesislab.gtube.model.dto.UserDto;
import com.genesislab.gtube.entity.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService extends OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    void insert(UserDto userDto);
    void update(UserDto userDto);
    void delete(Long id);
    User findById(Long id);
}
