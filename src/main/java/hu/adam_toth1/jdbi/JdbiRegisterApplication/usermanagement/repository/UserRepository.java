package hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);

}
