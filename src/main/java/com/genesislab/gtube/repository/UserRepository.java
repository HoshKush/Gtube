package com.genesislab.gtube.repository;

import com.genesislab.gtube.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where email=:email")
    User findByEmail(@Param("email") String email);
}
