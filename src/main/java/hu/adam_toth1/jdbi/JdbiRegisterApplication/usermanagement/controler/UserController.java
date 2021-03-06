package hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.model.User;
import hu.adam_toth1.jdbi.JdbiRegisterApplication.usermanagement.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<User> users = repository.findAll();
        model.addAttribute("listUsers", users);
        return "users";
    }

}
