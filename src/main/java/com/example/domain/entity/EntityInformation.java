package com.example.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class EntityInformation implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String local;
    private String race;
    private String bear;
    private String gender;
    private String image;


    public EntityInformation(){}

    public EntityInformation(long id, String name, String description, String local, String gender,
                             String race, String bear, String image){
        this.id = id;
        this.name = name;
        this.description = description;
        this.local = local;
        this.gender = gender;
        this.race = race;
        this.bear = bear;
        this.image = image;
    }

}
