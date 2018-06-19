package com.example.repository;


import com.example.domain.model.book.Book;
import com.example.domain.model.informacoes.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("informationRepository")
public interface InformationRepository extends JpaRepository<Information, Long> {

}
