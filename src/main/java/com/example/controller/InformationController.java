package com.example.controller;


import com.example.domain.entity.EntityBook;
import com.example.domain.entity.EntityInformation;
import com.example.domain.factory.BookFactory;
import com.example.domain.model.book.Book;
import com.example.domain.model.informacoes.Information;
import com.example.service.BookService;
import com.example.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api")
public class InformationController {

    @Autowired
    private InformationService informationService;
//    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(value = {"/send"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody Information information) {
        try {
//            bookService.create(information);
            // Change for information
            return new ResponseEntity(information, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(information, HttpStatus.CONFLICT);
        }
    }

//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping(value = {"/animals"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Information>> findAll() {
//        List<Book> lista = null;
        List<Information> lista = null;
        try {
            lista = informationService.findAll();
            // Change for information
            return new ResponseEntity<List<Information>>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Information>>(lista, HttpStatus.NO_CONTENT);
        }
    }

//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping(value = {"/animal/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<EntityInformation> findById(@PathVariable(value = "id") Long id) {
        Book book = null;
        Information information = null;
        EntityInformation entityInformation = null;
        try {
//            book = bookService.findById(id);
//            entityBook = BookFactory.createFactoryBook(book);
            return new ResponseEntity<EntityInformation>(entityInformation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<EntityInformation>(entityInformation, HttpStatus.NO_CONTENT);
        }
    }

    //Add Later!


//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @RequestMapping(value = {"/books"}, method = RequestMethod.DELETE)
//    public ResponseEntity<?> delete(@RequestParam(name = "id") long book) {
//        try {
//            bookService.delete(book);
//            return new ResponseEntity(book, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(book, HttpStatus.EXPECTATION_FAILED);
//        }
//    }


    //Add later

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @RequestMapping(value = {"/books"}, method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@RequestBody Book book) {
//
//        try {
//            bookService.update(book);
//            return new ResponseEntity(book, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(book, HttpStatus.EXPECTATION_FAILED);
//        }
//    }
}
