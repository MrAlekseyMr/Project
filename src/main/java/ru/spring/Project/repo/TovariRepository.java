package ru.spring.Project.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Project.Models.News;
import ru.spring.Project.Models.Tovari;
import ru.spring.Project.Models.Users;

import java.util.List;

public interface TovariRepository extends CrudRepository<Tovari, Long> {
    public List<Tovari> findByNameContains(String name);
    public List<News> findByName(String name);
}
