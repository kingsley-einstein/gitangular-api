package com.gitangular.graph.resolvers;

import java.util.UUID;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gitangular.graph.models.Location;
import com.gitangular.graph.models.Picture;
import com.gitangular.graph.models.User;
import com.gitangular.graph.repositories.LocationRepository;
import com.gitangular.graph.repositories.PictureRepository;
import com.gitangular.graph.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Value("${hasher}")
    private String hasher;

    public User create(String email, String username, String password, String github) {
        User user = new User(email, username, encoder.encode(password), github,
                BCrypt.hashpw(UUID.randomUUID().toString() + "" + hasher, BCrypt.gensalt()));

        return userRepository.save(user);
    }

    public User update(Long id, String email, String password) {
        User user = userRepository.findById(id).get();
        user.setEmail(email);
        user.setPassword(encoder.encode(password));

        return userRepository.save(user);
    }

    public boolean delete(Long id) {
        userRepository.deleteById(id);

        return true;
    }

    public Picture upload(Long user, String mimeType, String binaryContent, String name) {
        User theUser = userRepository.findById(user).get();
        Picture picture = new Picture(mimeType, binaryContent, name);
        Picture savedPicture = pictureRepository.save(picture);
        theUser.setPicture(savedPicture);
        userRepository.save(theUser);

        return savedPicture;
    }

    public Location newLocation(Long user, Integer latitude, Integer longitude) {
        User theUser = userRepository.findById(user).get();
        Location location = theUser.getLocation();

        if (location != null) {
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            Location updatedLocation = locationRepository.save(location);
            theUser.setLocation(updatedLocation);

            userRepository.save(theUser);

            return updatedLocation;
        } else {
            location = new Location(latitude, longitude);
            Location savedLocation = locationRepository.save(location);
            theUser.setLocation(savedLocation);

            userRepository.save(theUser);

            return savedLocation;
        }
    }
}