package hu.adam_toth1.jdbi.JdbiRegisterApplication.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.authentication.model.CustomUserDetails;
import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;
import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
