package com.genesislab.gtube.service;


import com.genesislab.gtube.entity.User;
import com.genesislab.gtube.exception.NoSuchUserException;
import com.genesislab.gtube.model.GoogleOAuth2User;
import com.genesislab.gtube.model.dto.UserDto;
import com.genesislab.gtube.repository.UserRepository;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration()
                                        .getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                                        .getProviderDetails()
                                        .getUserInfoEndpoint()
                                        .getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        return GoogleOAuth2User.builder()
                .attributes(attributes)
                .build();
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
        if(userDto.getName() != null) user.setName(userDto.getName()                    );
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
