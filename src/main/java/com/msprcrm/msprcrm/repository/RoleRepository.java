package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.ERole;
import com.msprcrm.msprcrm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}