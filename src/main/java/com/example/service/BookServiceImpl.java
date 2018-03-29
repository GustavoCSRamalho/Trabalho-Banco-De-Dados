package com.example.service;

import com.example.model.Book;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService{


	@Autowired
	private BookRepository bookRepository;

    @Override
    public void delete(long id) {
        Book bookD = bookRepository.findOne(id);
        bookRepository.delete(bookD);
    }

    @Override
    public void update(Book book) {
        Book bookUp = bookRepository.findOne(book.getId());
        bookUp.setAuthor(book.getAuthor());
        bookUp.setDescription(book.getDescription());
        bookUp.setPrice(book.getPrice());
        bookUp.setTitle(book.getTitle());
        bookRepository.save(bookUp);
    }

    @Override
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(long id){
        return bookRepository.findOne(id);
    }
}
