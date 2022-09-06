package ru.spring.Project.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Project.Models.News;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    public List<News> findByTitle(String title);
    public List<News> findByTitleContains(String title);
}
