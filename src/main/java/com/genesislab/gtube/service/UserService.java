package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import com.genesislab.gtube.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(UserDto userDto);
    void update(UserDto userDto);
    void delete(Long id);
    UserDto findById(Long id);
    User buildEntityFrom(UserDto userDto);
}
