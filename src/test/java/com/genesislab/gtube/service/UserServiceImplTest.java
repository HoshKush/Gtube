package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import com.genesislab.gtube.entity.User;
import com.genesislab.gtube.repository.UserRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    static UserDto userDtoForTest = UserDto.builder()
                    .email("test")
                    .name("2222")
                    .phone("3333")
                    .password("4444")
                    .role("USER")
                    .build();

    @BeforeEach
    void init() throws Exception {
        createUserForTest();
    }


    private void createUserForTest() {
        User test = userRepository.save(
                User.from(userDtoForTest)
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
                .role("USER")
                .build();

        //when
        userService.insert(userDto);

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
                .email(userDtoForTest.getEmail())
                .name("modified")
                .build();

        //when
        userService.update(userDto);
        User actual = userRepository.findByEmail(userDto.getEmail());

        //then
        org.assertj.core.api.Assertions.assertThat(actual.getName()).isEqualTo(userDto.getName());
    }




}