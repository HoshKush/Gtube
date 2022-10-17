package com.genesislab.gtube.entity;

import com.genesislab.gtube.dto.UserDto;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String phone;

    @ManyToMany
    @JoinTable(name = "USERS_ROLES",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    public static User from(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .roles(
                        userDto.getRoles().stream().map(name ->
                                Role.builder().name(name).build()
                        ).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + String.join("/", roles.stream().map(Role::getName).toArray(String[]::new)) +
                '}';
    }
}
