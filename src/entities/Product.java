package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Product implements Serializable {
    private int id;
    private String name, category;
    private float price;
    private LocalDate expiration_date, created_at;

    public Product(int id, String name, String category, float price, LocalDate expiration_date, LocalDate created_at) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.expiration_date = expiration_date;
        this.created_at = created_at;
    }

    public Product(String name, String category, float price, LocalDate expiration_date, LocalDate created_at) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.expiration_date = expiration_date;
        this.created_at = created_at;
    }

    public Product(String text, String text1, float v, String text2) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Producto id=" + id + "\tNome=" + name + "\tPreço=" + price + "\tCategoria=" + category + "\tData de Expiração=" + expiration_date + "\tCriado em =" + created_at;
    }
}
