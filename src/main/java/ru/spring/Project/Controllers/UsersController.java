package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.Project.Models.Users;
import ru.spring.Project.repo.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Users> user = userRepository.findById(id);
        ArrayList<Users> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("users", arrayList);
        return "users/User";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/users/";
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/users/";
        }
        Optional<Users> user = userRepository.findById(id);
        ArrayList<Users> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("users", arrayList);
        return "users/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @RequestParam("Familia") String familia,
                        @RequestParam("Ima") String ima,
                        @RequestParam("Work") String work,
                        @RequestParam("Otdel") String otdel,
                        @RequestParam("Zaraplata") Double zarplata,
                        Model model) {

        Users user = userRepository.findById(id).orElseThrow();

        user.setFamilia(familia);
        user.setIma(ima);
        user.setWork(work);
        user.setOtdel(otdel);
        user.setZaraplata(zarplata);

        userRepository.save(user);
        return "redirect:/users/"+Long.toString(id);
    }
}
