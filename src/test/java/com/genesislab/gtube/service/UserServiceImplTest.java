package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import com.genesislab.gtube.entity.Role;
import com.genesislab.gtube.entity.RoleEnum;
import com.genesislab.gtube.entity.User;
import com.genesislab.gtube.repository.RoleRepository;
import com.genesislab.gtube.repository.UserRepository;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;

    @BeforeEach
    void init() throws Exception {
        for(RoleEnum role : RoleEnum.values()) {
            if(roleRepository.findByName(role.name()) == null) {
                roleRepository.save(Role.builder().name(role.name()).build());
            }
        }
    }


    @Test
    @Transactional
    void 회원가입_성공() throws Exception {
        //given
        UserDto userDto = UserDto.builder()
                .email("1112")
                .name("2222")
                .phone("3333")
                .password("4444")
                .roles(Arrays.stream(RoleEnum.values()).map(RoleEnum::name).collect(Collectors.toSet()))
                .build();

        //when
        Set<Role> roles = userDto.getRoles().stream()
                .map(role -> roleRepository.findByName(role))
                .collect(Collectors.toSet());

        User expect = userRepository.save(
                User.builder()
                        .name(userDto.getName())
                        .phone(userDto.getPhone())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .roles(roles)
                        .build()
        );


        //then
        User actual = userRepository.findByEmail(userDto.getEmail());
        System.out.println(actual);
        Assertions.assertThat(actual).isEqualTo(expect);
    }

}