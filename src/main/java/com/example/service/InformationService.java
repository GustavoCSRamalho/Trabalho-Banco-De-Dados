package com.example.service;

import com.example.domain.model.book.Book;
import com.example.domain.model.informacoes.Information;

import java.util.List;

public interface InformationService {
	 void delete(long id);
	 void update(Information information);
	 void create(Information information);
     List<Information> findAll();
     Information findById(long id);
}
