package hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.repository;

import java.sql.Connection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;

@Repository
public class UserRepository {

    @Autowired
    private DataSource dataSource;

    Connection connection = null;
    Handle handle = null;
    UserSQL userSQL = null;

    @PostConstruct
    public void init() {
        connection = DataSourceUtils.getConnection(dataSource);
        handle = DBI.open(connection);
        userSQL = handle.attach(UserSQL.class);
    }

    public List<User> findAll() {
        return userSQL.findAll();
    }

    public User findByEmail(String email) {
        return userSQL.findByEmail(email);
    }

    public Integer save(User user) {
        return userSQL.save(user);
    }

}
