package ru.spring.Project.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;

    @NotBlank(message = "Заполните поле")
    @Size(message = "Укажите минимум 2 символа, Максимум 150",min = 2,max=150)
    String familia,ima;
    @NotBlank(message = "Заполните поле")
    @Size(message = "Укажите минимум 2 символа, Максимум 250",min = 2,max=250)
    String work,otdel;
    @DecimalMin(message =  "МРОТ составляет 13 890 рублей. Меньше зарплата быть не может", value= "13890")
    @NotNull(message = "Заполните поле")
    Double zaraplata;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="snils_id")
    private SNILS snils;

    @ManyToMany
    @JoinTable (name="redactor_news",
            joinColumns=@JoinColumn (name="redactor_id"),
            inverseJoinColumns=@JoinColumn(name="news_id"))
    public List<News> news;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Users() {
    }

    public SNILS getSnils() {
        return snils;
    }

    public void setSnils(SNILS snils) {
        this.snils = snils;
    }

    public Users(Users newUser, SNILS snils) {
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
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
