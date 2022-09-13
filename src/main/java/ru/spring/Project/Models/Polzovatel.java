package ru.spring.Project.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Polzovatel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public String name;

    @OneToMany (mappedBy = "polzovatel", fetch = FetchType.EAGER)
    private Collection<PolzovateliDetails> userDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Polzovatel() {
    }

    public Polzovatel(long id, String name, Collection<PolzovateliDetails> userDetails) {
        this.id = id;
        this.name = name;
        this.userDetails = userDetails;
    }
}
