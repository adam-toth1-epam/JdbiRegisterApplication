package hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.repository;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.mapper.UserMapper;
import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;

@RegisterMapper(UserMapper.class)
public interface UserSQL {

    @SqlQuery("SELECT * FROM users WHERE email = :email")
    User findByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM users")
    List<User> findAll();

    @SqlUpdate("INSERT INTO users (email, password, first_name, last_name) VALUES (:email, :password, :firstName, :lastName)")
    Integer save(@BindBean User user);

}
