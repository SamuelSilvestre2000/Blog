package com.samuelsilvestre.graphql.blog.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    // Custom query methods (if needed) can be added here
}
