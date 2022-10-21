package com.genesislab.gtube.entity;

import com.genesislab.gtube.model.dto.UserDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oauthId;

    @Column(unique = true)
    private String email;

    private String name;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User from(UserDto userDto) {
        return User.builder()
                .oauthId(userDto.getOauthId())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .name(userDto.getName())
                .role(userDto.getRoleAsEnum())
                .build();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role.name() +
                '}';
    }
}
