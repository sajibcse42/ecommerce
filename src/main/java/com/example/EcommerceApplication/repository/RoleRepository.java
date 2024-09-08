package com.example.EcommerceApplication.repository;

import com.example.EcommerceApplication.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRoles, Long> {
    Optional<UserRoles> findByName(String name);
}

