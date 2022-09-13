package ru.spring.Project.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;
    @NotEmpty(message = "Ты чо МПТшник чтоль?! Не тупи, заполни поле")
    @Size(message = "Строка не может быть больше 150 символов",min = 3,max=150)
    String title;
    @NotEmpty(message = "Ты чо МПТшник чтоль?! Не тупи, заполни поле")
    @Size(message = "Строка не может быть больше 10000 символов",min = 3,max=10000)
    String body_text;

    @NotNull(message = "Нпишите автора")
    @NotEmpty(message = "Ты чо МПТшник чтоль?! Не тупи, заполни поле")
    String author;
    @NotNull(message = "Число не может быть пустым")
    @Min(message = "Количество не может быть отрицательным", value = 0)
    @Max(message = "Количество не может быть больше 100000", value = 100000)
    Integer likes, views;

    @ManyToMany
    @JoinTable(name="redactor_news",
            joinColumns=@JoinColumn(name="news_id"),
            inverseJoinColumns=@JoinColumn(name="redactor_id"))
    public List<Users> redactors;

    public List<Users> getRedactors() {
        return redactors;
    }

    public void setRedactors(List<Users> redactors) {
        this.redactors = redactors;
    }
//@NotBlank //Обрезает пробелы по бокам, чтобы строка не была пустой
    //@DecimalMax()
    //@DecimalMin()
    //@Digits() //Проверяет вещественные числа
    //@Negative //Проверка на отрицательные числа БЕЗ НУЛЯ
    //@NegativeOrZero //Проверка на отрицательные числа с нулём
    //@Positive
    //@PositiveOrZero
    //@Future
    //@FutureOrPresent
    //@Past
    //@PastOrPresent
    //@Email
    //@AssertTrue
    //@AssertFalse

    //Alt+0(Insert) - Generate
    public Long getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }

    public String getBody_text() {
        return body_text;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getViews() {
        return views;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody_text(String body_text) {
        this.body_text = body_text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public News(String title, String body_text, String author, Integer likes, Integer views) {
        this.title = title;
        this.body_text = body_text;
        this.author = author;
        this.likes = likes;
        this.views = views;
    }

    public News() {
    }
}
