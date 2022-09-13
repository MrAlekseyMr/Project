package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.Project.Models.News;
import ru.spring.Project.Models.SNILS;
import ru.spring.Project.Models.Tovari;
import ru.spring.Project.Models.Users;
import ru.spring.Project.repo.SnilsRepository;
import ru.spring.Project.repo.UsersRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private SnilsRepository snilsRepository;

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
        model.addAttribute("users", new Users());
        Iterable<SNILS> snils = snilsRepository.findAll();
        model.addAttribute("snils", snils);
        return "users/add";
    }

    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("users") @Valid Users newUser,@RequestParam Long snils, BindingResult result,
            Model model)
    {
        if(result.hasErrors())
            return "users/add";
        else {
            userRepository.save(newUser);
            return "redirect:/users/";
        }
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
        model.addAttribute("users2", new Users());
        return "users/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("users2") @Valid Users newUser, BindingResult result,
                        Model model) {
        if(result.hasErrors()) {
            Optional<Users> user = userRepository.findById(id);
            ArrayList<Users> arrayList = new ArrayList<>();
            user.ifPresent(arrayList::add);
            model.addAttribute("users", arrayList);
            return "users/edit";
        }
        else {
            Users user = userRepository.findById(id).orElseThrow();
            newUser.setID(user.getID());
            userRepository.save(newUser);
            return "redirect:/users/"+Long.toString(id);
        }
    }
}
