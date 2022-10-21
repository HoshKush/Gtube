package com.genesislab.gtube.service;

import com.genesislab.gtube.model.dto.UserDto;
import com.genesislab.gtube.entity.User;
import com.genesislab.gtube.exception.NoSuchUserException;
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
                    .oauthId("test")
                    .role("USER")
                    .build();

    @BeforeEach
    void init() throws Exception {
        createUserForTest();
    }


    private void createUserForTest() {

        if(userRepository.findByEmail(userDtoForTest.getEmail()) == null) {
            userRepository.save(User.from(userDtoForTest));
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
                .oauthId("5555")
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
            if(!userDto.getOauthId().equals(actual.getOauthId())) return false;
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
        User actual = userRepository.findByEmail(userDto.getEmail());
        userDto.setId(actual.getId());
        userService.update(userDto);

        //then
        System.out.println(actual);
        org.assertj.core.api.Assertions.assertThat(actual.getName()).isEqualTo(userDto.getName());
    }

    @Test
    @Transactional
    void 회원삭제_성공() throws Exception{
        //given
        Long id = userRepository.findByEmail(userDtoForTest.getEmail()).getId();

        //when
        userService.delete(id);

        //then
        org.assertj.core.api.Assertions.assertThatExceptionOfType(NoSuchUserException.class)
                .isThrownBy(() -> userService.findById(id));
    }





}