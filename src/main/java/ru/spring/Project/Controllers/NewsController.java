package ru.spring.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.Project.Models.News;
import ru.spring.Project.repo.NewsRepository;

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
        return "news/add";
    }

    @PostMapping("/add")
    public String AddPost(
            @RequestParam("title") String title,
            @RequestParam("body_text") String body_text,
            @RequestParam("author") String author,
            @RequestParam("views") Integer views,
            @RequestParam("likes") Integer likes,
            Model model)
    {
        News newOne = new News(title,body_text,author,likes,views);
        newsRepository.save(newOne);
        return "redirect:/news/";
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
}
