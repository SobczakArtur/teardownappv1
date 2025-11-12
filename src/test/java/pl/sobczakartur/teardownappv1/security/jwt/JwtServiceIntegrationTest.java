package pl.sobczakartur.teardownappv1.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class JwtServiceIntegrationTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateAndValidateToken() {

        UserDetails userDetails = new User("testUser", "password", Collections.emptyList());

        String token = jwtService.generateToken(userDetails);
        boolean isValid = jwtService.isTokenValid(token, userDetails);
        assertTrue(isValid, "Token should be valid for the given user");
    }
}