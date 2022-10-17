package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import com.genesislab.gtube.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void insert(UserDto userDto);
    void update(UserDto userDto);
    void delete(Long id);
    User findById(Long id);
}
