package com.genesislab.gtube.service;

import com.genesislab.gtube.dto.UserDto;
import com.genesislab.gtube.entity.User;
import com.genesislab.gtube.exception.NoSuchUserException;
import com.genesislab.gtube.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    @Transactional
    public void insert(UserDto userDto) {
        userRepository.save(
                User.from(userDto)
        );
    }

    @Override
    @Transactional
    public void update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(userDto.getName() != null) user.setName(userDto.getName());
        if(userDto.getPhone() != null) user.setPhone(userDto.getName());
        if(userDto.getRole() != null) user.setRole(userDto.getRoleAsEnum());
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException("존재하지 않는 사용자입니다."));
    }

    public boolean exists(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail()) != null;
    }

}
