package com.example.domain.factory;

import com.example.domain.model.book.Book;
import com.example.domain.entity.EntityBook;

public class BookFactory {

    public static EntityBook createFactoryBook(Book book){
        return new EntityBook(book.getId(),book.getTitle(),book.getDescription(),book.getPrice(),
                book.getAuthor());
    }
}
