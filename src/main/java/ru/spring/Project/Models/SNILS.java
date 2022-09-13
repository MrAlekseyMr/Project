package ru.spring.Project.Models;

import javax.persistence.*;

@Entity
public class SNILS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String snils;

    @OneToOne(optional = true, mappedBy = "snils")
    private Users owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public SNILS(Long id, String snils, Users owner) {
        this.id = id;
        this.snils = snils;
        this.owner = owner;
    }

    public SNILS() {
    }
}
