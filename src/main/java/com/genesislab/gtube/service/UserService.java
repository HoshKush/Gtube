package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(UserDto userDto);
    void update(UserDto userDto);
    void delete(Long id);
    UserDto findById(Long id);
}
