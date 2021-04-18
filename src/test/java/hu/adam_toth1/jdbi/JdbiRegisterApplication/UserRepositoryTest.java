package hu.adam_toth1.jdbi.JdbiRegisterApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;
import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUser() {
        // Given
        User user = createUser();

        // When
        User storedUser = userRepository.save(user);

        // Then
        User fetchedUser = testEntityManager.find(User.class, storedUser.getId());
        assertThat(fetchedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        // Given
        String email = "adam_toth1@epam.com";

        // When
        User user = userRepository.findByEmail(email);

        // Then
        assertThat(user).isNotNull();
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
