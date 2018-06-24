package com.example.domain.model.informacoes;


import com.sun.org.apache.xpath.internal.operations.Mult;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;

@Data
public class InformationRequest implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String local;

    private String race;

    private String gender;

    private String bear;

    public InformationRequest() {
    }
//
    public InformationRequest(String name, String description, String local, String race,
                              String gender, String bear) {
        setName(name);
        setDescription(description);
        setLocal(local);
        setRace(race);
        setGender(gender);
        setBear(bear);
    }
}

