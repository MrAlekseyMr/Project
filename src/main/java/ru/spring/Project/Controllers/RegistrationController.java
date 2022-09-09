package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.Project.Models.AuthUser;
import ru.spring.Project.Models.Role;
import ru.spring.Project.repo.AuthUserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private AuthUserRepository userRepository;

    @GetMapping("registration")
    public String reg_view(Model model)
    {
        return "registration";
    }

    @PostMapping("registration")
    public String reg_action(AuthUser user, Model model)
    {
        AuthUser userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB != null)
        {
            model.addAttribute("error","Такой пользователь уже существует");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.AUTHUSER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
