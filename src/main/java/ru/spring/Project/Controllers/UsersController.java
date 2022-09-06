package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.Project.Models.Users;
import ru.spring.Project.repo.UsersRepository;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Users> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "users/Index";
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        return "users/add";
    }

    @PostMapping("/add")
    public String AddPost(
            @RequestParam("Familia") String familia,
            @RequestParam("Ima") String ima,
            @RequestParam("Work") String work,
            @RequestParam("Otdel") String otdel,
            @RequestParam("Zaraplata") Double zarplata,
            Model model)
    {
        Users newOne = new Users(familia,ima,work,otdel,zarplata);
        userRepository.save(newOne);
        return "redirect:/users/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("title") String title,
            Model model)
    {
        List<Users> usersList = userRepository.findByFamiliaContains(title);
        model.addAttribute("users",usersList);
        return "users/Index";
    }
}
