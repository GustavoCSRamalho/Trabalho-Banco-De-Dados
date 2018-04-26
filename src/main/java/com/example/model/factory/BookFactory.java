package com.example.model.factory;

import com.example.model.entity.Book;
import com.example.model.entity.EntityBook;

public class BookFactory {

    public static EntityBook createFactoryBook(Book book){
        return new EntityBook(book.getId(),book.getTitle(),book.getDescription(),book.getPrice(),
                book.getAuthor());
    }
}
