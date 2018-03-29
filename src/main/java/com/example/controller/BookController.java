package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping(value = {"/books"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void create(@Valid @RequestBody Book book) {
        bookService.create(book);
    }

    @RequestMapping(value = {"/books"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = {"/books/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<Book> findById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Book>(bookService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"/books"}, method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestBody Book book ) {
        try {
            bookService.delete(book.getId());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"/books"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Book book) {
        try {
            bookService.update(book);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
