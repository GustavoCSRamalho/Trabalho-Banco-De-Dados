package com.example.teste.controller.service;

import com.example.teste.controller.model.security.Book;

import java.util.List;

public interface BookService {
	public void delete(long id);
	public void update(Book book);
	public void create(Book book);
    public List<Book> findAll();
    public Book findById(long id);
}
