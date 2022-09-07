package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.Project.Models.Tovari;
import ru.spring.Project.Models.Users;
import ru.spring.Project.repo.TovariRepository;
import ru.spring.Project.repo.UsersRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Tovari> tovar = tovariRepository.findById(id);
        ArrayList<Tovari> arrayList = new ArrayList<>();
        tovar.ifPresent(arrayList::add);
        model.addAttribute("tovari", arrayList);
        return "tovari/Tovar";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        tovariRepository.deleteById(id);
        return "redirect:/tovari/";
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!tovariRepository.existsById(id)) {
            return "redirect:/tovari/";
        }
        Optional<Tovari> tovar = tovariRepository.findById(id);
        ArrayList<Tovari> arrayList = new ArrayList<>();
        tovar.ifPresent(arrayList::add);
        model.addAttribute("tovari", arrayList);
        return "tovari/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @RequestParam("name") String name,
                        @RequestParam("articul") String articul,
                        @RequestParam("type") String type,
                        @RequestParam("price") Double price,
                        @RequestParam("srok") String srok,
                        Model model) {

        Tovari tovar = tovariRepository.findById(id).orElseThrow();

        tovar.setName(name);
        tovar.setArticul(articul);
        tovar.setType(type);
        tovar.setPrice(price);
        tovar.setSrokGodnosti(srok);

        tovariRepository.save(tovar);
        return "redirect:/tovari/"+Long.toString(id);
    }
}
