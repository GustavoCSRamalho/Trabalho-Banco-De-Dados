package com.example.domain.model.informacoes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "information")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@Data
public class Information implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide a name")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "*Please provide a description")
    private String description;

    @Column(name = "local")
    @NotEmpty(message = "*Please provide a local")
    private String local;

    @Column(name = "race")
    @NotEmpty(message = "*Please provide the race")
    private String race;

    @Column(name = "gender")
    @NotEmpty(message = "*Please provide the gender")
    private String gender;

    @Column(name = "bear")
    @NotEmpty(message = "*Please provide the bearing")
    private String bear;

    @Column(name = "image")
    private String image;

    //Adicionar ""Porte "" de cachorro, esta pala em ingles "Porte"

    public Information() {
    }

    public Information(String name, String description, String local, String race,
                       String gender,String bear, String image) {
        setName(name);
        setDescription(description);
        setLocal(local);
        setRace(race);
        setGender(gender);
        setBear(bear);
        setImage(image);
    }
}

