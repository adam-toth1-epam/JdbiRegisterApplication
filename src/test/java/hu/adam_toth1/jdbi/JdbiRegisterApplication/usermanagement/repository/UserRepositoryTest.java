package hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Disabled
    public void testCreateUser() {
        // Given
        User user = createUser();

        // When
        Integer storedUserId = userRepository.save(user);

        // Then
        User fetchedUser = userRepository.findByEmail(user.getEmail());
        assertThat(fetchedUser)
                .isNotNull()
                .extracting(User::getEmail)
                .isEqualTo(user.getEmail());
    }

    @Test
    @Disabled
    public void testFindUserByEmail() {
        // Given
        String email = "adam_toth1@epam.com";
        User user = createUser();
        userRepository.save(user);

        // When
        User storedUser = userRepository.findByEmail(email);

        // Then
        assertThat(storedUser).isNotNull();
    }

    private User createUser() {
        return User
                .builder()
                .email("adam_toth1@epam.com")
                .password("Goat666")
                .firstName("Adam")
                .lastName("Toth")
                .build();
    }

}