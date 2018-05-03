package com.example.integration;

import com.example.config.AbstractApplicationTest;
import com.example.domain.model.book.Book;
import com.example.domain.model.json.request.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookTeste extends AbstractApplicationTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private String token = null;
    private List<Book> listaBook = null;


    @Before
    public void loadContext() throws Exception {
        super.setUpContext();

        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "123");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        token = super.mockMvcPerformResult("/api/auth", jsonInString, MediaType.APPLICATION_JSON_VALUE);

    }

    @Test
    public void testCrud() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Book book = new Book("TesteTitulo", "Descricao zoada", "RS 135", "Zoado");
//        EntityBook entityBook = BookFactory.createFactoryBook(book);
        String jsonInString = mapper.writeValueAsString(book);
        int status = super.mockMvcPerformAuthenticatedPostStatus("/api/books", jsonInString,
                MediaType.APPLICATION_JSON_VALUE, status().isCreated(), this.token);

        Assert.assertEquals("Teste Create : ",201,status);


        String lista = super.mockMvcPerformAuthenticatedGetAll("/api/books", token);

        Assert.assertNotNull("Teste lista not null : ",lista);

        List<Book> listaBooks = mapper.readValue(lista, mapper.getTypeFactory().constructCollectionType(List.class,Book.class));
        this.listaBook = listaBooks;

        Book bookTeste = new Book();
        for(int i = 0; i < listaBooks.size();i++){
            if(listaBooks.get(i).getTitle().equals("TesteTitulo")){
                bookTeste = listaBooks.get(i);
                break;
            }
        }

        bookTeste.setTitle("TesteTituloMudado");
        jsonInString = mapper.writeValueAsString(bookTeste);
        status = super.mockMvcPerformAuthenticatedPutResult("/api/books", jsonInString, MediaType.APPLICATION_JSON_VALUE, status().isOk(), token);
        Assert.assertEquals("Teste update : ",200, status);

        status = super.mockMvcPerformAuthenticatedDeleteResult("/api/books", "id",bookTeste.getId().toString(), MediaType.APPLICATION_JSON_VALUE, status().isOk(), token);
        Assert.assertEquals("Teste delete : ",200, status);
    }
}
