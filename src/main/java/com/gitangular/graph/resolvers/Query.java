package com.gitangular.graph.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gitangular.graph.models.User;
import com.gitangular.graph.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
}