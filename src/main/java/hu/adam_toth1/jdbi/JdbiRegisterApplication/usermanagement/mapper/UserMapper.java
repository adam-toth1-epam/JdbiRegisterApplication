package hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;

public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return User
                .builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .firstName(rs.getString("first_name"))
                .lastName("last_name")
                .build();
    }

}
