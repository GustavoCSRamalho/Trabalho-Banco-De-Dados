package com.example.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class EntityBook implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String price;
    private String author;

    public EntityBook(){}

    public EntityBook(long id, String title, String description, String price,String author){
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.author = author;
    }

}
