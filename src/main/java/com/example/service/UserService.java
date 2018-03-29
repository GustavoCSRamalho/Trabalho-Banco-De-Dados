package com.example.service;

import com.example.model.Book;
import com.example.model.User;

import java.util.List;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> findAllUser();
    public List<Book> findAllBooks();
}
