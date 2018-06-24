package com.example.domain.model.informacoes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "information")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@Data
@Component
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

    @Column(name = "file")
    @NotEmpty(message = "*Please provide the file")
    private String file;

    public Information() {
    }

    public Information(InformationRequest informationRequest) {
        setId(informationRequest.getId());
        setName(informationRequest.getName());
        setDescription(informationRequest.getDescription());
        setLocal(informationRequest.getLocal());
        setRace(informationRequest.getRace());
        setGender(informationRequest.getGender());
        setBear(informationRequest.getBear());

    }
}

