package com.example.domain.model.book;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@Data
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotEmpty(message = "*Please provide a title")
    private String title;

    @Column(name = "description")
    @NotEmpty(message = "*Please provide a description")
    private String description;

    @Column(name = "price")
    @NotEmpty(message = "*Please provide a price")
    private String price;

    @Column(name = "author")
    @NotEmpty(message = "*Please provide the name  of the author")
    private String author;

    public Book(){}
    public Book(String title, String description, String price,String author){
        setTitle(title);
        setAuthor(author);
        setDescription(description);
        setPrice(price);

    }
    }

