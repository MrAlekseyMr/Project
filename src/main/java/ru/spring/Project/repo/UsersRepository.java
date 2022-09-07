package ru.spring.Project.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Project.Models.News;
import ru.spring.Project.Models.Users;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Long> {
    public List<Users> findByFamiliaContains(String familia);
    public List<News> findByFamilia(String familia);
}
