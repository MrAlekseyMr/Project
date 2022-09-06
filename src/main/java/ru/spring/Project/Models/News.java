package ru.spring.Project.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;
    String title, body_text, author;
    Integer likes, views;

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
