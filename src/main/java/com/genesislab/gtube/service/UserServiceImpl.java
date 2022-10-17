package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import com.genesislab.gtube.entity.Role;
import com.genesislab.gtube.entity.User;
import com.genesislab.gtube.repository.RoleRepository;
import com.genesislab.gtube.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void save(UserDto userDto) {
        Set<Role> roles = userDto.getRoles().stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());

        userRepository.save(
                User.builder()
                        .name(userDto.getName())
                        .phone(userDto.getPhone())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .roles(roles)
                .build()
        );
    }

    @Override
    public void update(UserDto userDto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

}
