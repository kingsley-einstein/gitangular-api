package com.gitangular.graph;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.gitangular.graph.models.User;
import com.gitangular.graph.repositories.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class GitangularApiTests {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Value("${hasher}")
    private String hasher;

    @Test
    public void contextLoaded() {
        User user = userRepo
                .save(new User("janez@app.com", "janez", encoder.encode(hasher), "janez", encoder.encode(hasher)));
        assertNotNull(user);
        assertTrue(encoder.matches(hasher, user.getPassword()));
    }
}