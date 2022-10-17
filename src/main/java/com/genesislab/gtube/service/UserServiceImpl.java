package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import com.genesislab.gtube.entity.Role;
import com.genesislab.gtube.entity.User;
import com.genesislab.gtube.repository.RoleRepository;
import com.genesislab.gtube.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
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
        userRepository.save(
                buildEntityFrom(userDto)
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

    public User buildEntityFrom(UserDto dto) {
        Set<Role> roles = dto.getRoles().stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());

        return User.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .roles(roles)
                .build();
    }

}
