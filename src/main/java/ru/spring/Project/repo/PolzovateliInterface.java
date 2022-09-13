package ru.spring.Project.repo;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;
import ru.spring.Project.Models.News;
import ru.spring.Project.Models.Polzovatel;

public interface PolzovateliInterface extends CrudRepository<Polzovatel, Long> {
    //Polzovatel findOne(ID primaryKey);
}
