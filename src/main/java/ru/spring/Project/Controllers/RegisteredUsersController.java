package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.Project.Models.AuthUser;
import ru.spring.Project.Models.Role;
import ru.spring.Project.repo.AuthUserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/registered")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RegisteredUsersController {
    @Autowired
    private AuthUserRepository userRepository;

    @GetMapping("/")
    public String all_users(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "registeredUsers/allUsers";
    }

    @GetMapping("/edit/{id}")
    public String user_role(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<AuthUser> user_raw = userRepository.findById(id);
        ArrayList<AuthUser> userArrayList = new ArrayList<>();

        user_raw.ifPresent(userArrayList::add);

        model.addAttribute("one_user",userArrayList);
        model.addAttribute("roles", Role.values());
        return "registeredUsers/editUserRole";
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public String edit_role(
            @RequestParam("userId") AuthUser user,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(name = "roles[]", required = false)
            String[] roles
    )
    {
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().clear();

        if (roles != null)
        {
            for (String role_name:
                    roles) {
                user.getRoles().add(Role.valueOf(role_name));
            }
        }
        userRepository.save(user);
        return "redirect:/registered/";
    }
}
