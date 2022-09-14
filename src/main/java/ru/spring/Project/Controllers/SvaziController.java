package ru.spring.Project.Controllers;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.Project.Models.*;
import ru.spring.Project.repo.*;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/svazi")
public class SvaziController {
    @Autowired
    private PolzovateliInterface polzovateliInterface;
    @Autowired
    private PolzovateliDetailsInterface polzovateliDetailsInterface;

    @GetMapping("addpolzdan")
    public String getaddpdan(Model model)
    {
        Iterable<Polzovatel> polzovatels = polzovateliInterface.findAll();
        model.addAttribute("f", polzovatels);
        return "svazi/addpolzdet";
    }

    @PostMapping("addpolzdan")
    public String postaddpgan(@RequestParam String phone, @RequestParam Long name, Model model)
    {
        Optional<Polzovatel> polz = polzovateliInterface.findById(name);
        Polzovatel o = polz.get();
        PolzovateliDetails person = new PolzovateliDetails(phone, o);
        polzovateliDetailsInterface.save(person);
        return "redirect:/svazi/addpolzdan";
    }

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("addredactors")
    private String addredacget(Model model){
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "svazi/addredactors";
    }
    @PostMapping("addredactors")
    private String addredacpost(@RequestParam Long news, @RequestParam Long use, Model model){
        Optional<Users> us = usersRepository.findById(use);
        Users user = us.get();
        Optional<News> ne = newsRepository.findById(news);
        News ng = ne.get();
        user.getNews().add(ng);
        ng.getRedactors().add(user);
        usersRepository.save(user);
        newsRepository.save(ng);
        return "redirect:/svazi/addredactors";
    }
}
