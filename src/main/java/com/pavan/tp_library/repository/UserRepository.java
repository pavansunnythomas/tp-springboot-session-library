package com.pavan.tp_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.tp_library.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByMembershipNumber(String membershipNumber);

    boolean existsByEmail(String email);

    boolean existsByMembershipNumber(String membershipNumber);
}