package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.Project.Models.News;
import ru.spring.Project.repo.NewsRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news",news);
        return "news/Index";
    }

    @GetMapping("/add")
    public String AddGet(Model model)
    {
        model.addAttribute("news", new News());
        return "news/add";
    }

    @GetMapping("/search")
    public String AddPost(
            @RequestParam("title") String title,
            Model model)
    {
        List<News> newsList = newsRepository.findByTitleContains(title);
        model.addAttribute("news",newsList);
        return "news/Index";
    }
    //@RequestMapping(value="/news", method=RequestMethod.POST)
    @PostMapping("/add")
    public String AddPost(
            @ModelAttribute("news") @Valid News newNews, BindingResult result,
            Model model)
    {
        if(result.hasErrors())
            return "news/add";
        else
        {
            newsRepository.save(newNews);
            return "redirect:/news/";
        }
    }
}
