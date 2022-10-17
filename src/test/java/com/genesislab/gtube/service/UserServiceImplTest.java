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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserService userService;

    static UserDto userDtoForTest = UserDto.builder()
                    .email("test")
                    .name("2222")
                    .phone("3333")
                    .password("4444")
                    .roles(Arrays.stream(RoleEnum.values()).map(RoleEnum::name).collect(Collectors.toSet()))
                    .build();

    @BeforeEach
    void init() throws Exception {
        insertRolesToDB();
        createUserForTest();
    }

    void insertRolesToDB() throws Exception {
        for(RoleEnum role : RoleEnum.values()) {
            if(roleRepository.findByName(role.name()) == null) {
                roleRepository.save(Role.builder().name(role.name()).build());
            }
        }
    }

    private void createUserForTest() {
        User test = userRepository.save(
                userService.buildEntityFrom(userDtoForTest)
        );

        if(userRepository.findByEmail(userDtoForTest.getEmail()) == null) {
            userRepository.save(test);
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
        userService.save(userDto);

        //then
        User actual = userRepository.findByEmail(userDto.getEmail());
        System.out.println(actual);
        Assertions.assertTrue(() -> {
            if(!userDto.getEmail().equals(actual.getEmail())) return false;
            if(!userDto.getName().equals(actual.getName())) return false;
            if(!userDto.getPhone().equals(actual.getPhone())) return false;
            if(!userDto.getPassword().equals(actual.getPassword())) return false;
            return true;
        });
    }

    @Test
    @Transactional
    void 회원정보수정_성공() throws Exception{
        //given
        UserDto userDto = UserDto.builder()
                .email("test")
                .build();

        //when


//        User expect = userRepository.save(
//                User.builder()
//                        .name(userDto.getName())
//                        .phone(userDto.getPhone())
//                        .email(userDto.getEmail())
//                        .password(userDto.getPassword())
//                        .roles(roles)
//                        .build()
//        );

        //then
    }




}