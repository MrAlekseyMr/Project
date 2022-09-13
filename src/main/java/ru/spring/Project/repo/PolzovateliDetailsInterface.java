package ru.spring.Project.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Project.Models.News;
import ru.spring.Project.Models.PolzovateliDetails;

public interface PolzovateliDetailsInterface extends CrudRepository<PolzovateliDetails, Long> {
}
