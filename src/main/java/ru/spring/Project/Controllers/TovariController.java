package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.Project.Models.Tovari;
import ru.spring.Project.Models.Users;
import ru.spring.Project.repo.TovariRepository;
import ru.spring.Project.repo.UsersRepository;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tovari")
public class TovariController {

    @Autowired
    private TovariRepository tovariRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Tovari> tovari = tovariRepository.findAll();
        model.addAttribute("tovari",tovari);
        return "tovari/Index";
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        return "tovari/add";
    }

    @PostMapping("/add")
    public String AddPost(
            @RequestParam("name") String name,
            @RequestParam("articul") String articul,
            @RequestParam("type") String type,
            @RequestParam("price") Double price,
            @RequestParam("srok") String srok,
            Model model)
    {
        Tovari newOne = new Tovari(name,articul,type,price,srok);
        tovariRepository.save(newOne);
        return "redirect:/tovari/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("title") String title,
            Model model)
    {
        List<Tovari> tovariList = tovariRepository.findByNameContains(title);
        model.addAttribute("tovari",tovariList);
        return "tovari/Index";
    }
}
