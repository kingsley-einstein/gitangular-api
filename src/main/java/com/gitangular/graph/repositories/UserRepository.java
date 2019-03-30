package com.gitangular.graph.repositories;

import com.gitangular.graph.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by generated token
     * 
     * @param token {@value:} String representation of token
     * @return User
     */
    public User findByToken(String token);

    /**
     * Finds a user by username
     * 
     * @param username {@value:} String representation of username
     * @return User
     */
    public User findByUsername(String username);
}