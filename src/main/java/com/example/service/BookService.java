package com.example.service;

import com.example.domain.model.book.Book;

import java.util.List;

public interface BookService {
	 void delete(long id);
	 void update(Book book);
	 void create(Book book);
     List<Book> findAll();
     Book findById(long id);
}
