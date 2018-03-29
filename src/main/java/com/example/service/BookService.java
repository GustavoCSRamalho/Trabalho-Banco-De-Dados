package com.example.service;

import com.example.model.Book;
import com.example.model.User;

import java.util.List;

public interface BookService {
	public void delete (long id);
	public void update (Book book);
	public void create (Book book);
    public List<Book> findAll();
    public Book findById(long id);
}
