package com.genesislab.gtube.repository;

import com.genesislab.gtube.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "from Role where name=:name")
    Role findByName(@Param("name") String name);
}
