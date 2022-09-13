package ru.spring.Project.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Project.Models.Polzovatel;
import ru.spring.Project.Models.SNILS;

public interface SnilsRepository extends CrudRepository<SNILS, Long> {
}
