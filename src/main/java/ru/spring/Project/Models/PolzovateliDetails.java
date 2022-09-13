package ru.spring.Project.Models;

import javax.persistence.*;

@Entity
public class PolzovateliDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public String phone;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public Polzovatel polzovatel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Polzovatel getPolzovatel() {
        return polzovatel;
    }

    public void setPolzovatel(Polzovatel polzovatel) {
        this.polzovatel = polzovatel;
    }

    public PolzovateliDetails() {
    }

    public PolzovateliDetails(String phone, Polzovatel polzovatel) {
        this.phone = phone;
        this.polzovatel = polzovatel;
    }
}
