package com.gitangular.graph.resolvers;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gitangular.graph.models.User;
import com.gitangular.graph.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User getById(Long id) {
        User user = userRepository.findById(id).get();

        return user;
    }

    public User getByToken(String token) {
        return userRepository.findByToken(token);
    }

    public User log(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            if (encoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new Exception("Incorrect password");
            }
        } else {
            throw new Exception("User not found");
        }
    }

    public List<User> getAllUsers(Integer page) {
        Pageable pageable = PageRequest.of(page, 12);
        Page<User> paged = userRepository.findAll(pageable);

        return paged.getContent();
    }
}