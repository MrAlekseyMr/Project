package ru.spring.Project.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;

    String familia,ima,work,otdel;
    Double zaraplata;

    public Users() {
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setOtdel(String otdel) {
        this.otdel = otdel;
    }

    public void setZaraplata(Double zaraplata) {
        this.zaraplata = zaraplata;
    }

    public Long getID() {
        return ID;
    }

    public String getFamilia() {
        return familia;
    }

    public String getIma() {
        return ima;
    }

    public String getWork() {
        return work;
    }

    public String getOtdel() {
        return otdel;
    }

    public Double getZaraplata() {
        return zaraplata;
    }

    public Users(String familia, String ima, String work, String otdel, Double zaraplata) {
        this.familia = familia;
        this.ima = ima;
        this.work = work;
        this.otdel = otdel;
        this.zaraplata = zaraplata;
    }
}
