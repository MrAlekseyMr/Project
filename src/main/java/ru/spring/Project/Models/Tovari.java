package ru.spring.Project.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Tovari {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;

    @NotBlank(message = "Строка не может быть пустой")
    @Size(message = "Минимум 3 символа и не более 150",min = 3,max=150)
    String name,type;
    @NotBlank(message = "Строка не может быть пустой")
    @Size(message = "Должно быть ровно 7 символов",min = 7,max=7)
    @Digits(message = "Может состоять только из чисел", integer = 7, fraction = 0)
    String articul;

    @DecimalMin(message =  "Минимум 1 рубль", value= "1")
    @NotNull(message = "Поле не может быть пустым")
    Double price;

/*    @NotNull(message = "Укажите дату")
    @FutureOrPresent(message = "Можно указать только текущую дату и будущую")
    Date srokGodnosti;*/

    public Tovari() {
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

/*
    public void setSrokGodnosti(String srokGodnosti) {
        this.srokGodnosti = srokGodnosti;
    }
*/

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getArticul() {
        return articul;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

/*    public String getSrokGodnosti() {
        return srokGodnosti;
    }*/

    public Tovari(String name, String articul, String type, Double price/*, String srokGodnosti*/) {
        this.name = name;
        this.articul = articul;
        this.type = type;
        this.price = price;
/*        this.srokGodnosti = srokGodnosti;*/
    }
}
