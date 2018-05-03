package com.example.controller;


import com.example.domain.model.book.Book;
import com.example.domain.entity.EntityBook;
import com.example.domain.factory.BookFactory;
import com.example.service.BookService;
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
public class BookController {

    @Autowired
    private BookService bookService;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = {"/books"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody Book book) {
        try {
            bookService.create(book);
            return new ResponseEntity(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(book, HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping(value = {"/books"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> findAll() {
        List<Book> lista = null;
        try {
            lista = bookService.findAll();
            return new ResponseEntity<List<Book>>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Book>>(lista, HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping(value = {"/books/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<EntityBook> findById(@PathVariable(value = "id") Long id) {
        Book book = null;
        EntityBook entityBook = null;
        try {
            book = bookService.findById(id);
            entityBook = BookFactory.createFactoryBook(book);
            return new ResponseEntity<EntityBook>(entityBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<EntityBook>(entityBook, HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = {"/books"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam(name = "id") long book) {
        try {
            bookService.delete(book);
            return new ResponseEntity(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(book, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = {"/books"}, method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Book book) {

        try {
            bookService.update(book);
            return new ResponseEntity(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(book, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
