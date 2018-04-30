package com.example.integration;

import com.example.config.AbstractApplicationTest;
import com.example.model.entity.Book;
import com.example.model.entity.EntityBook;
import com.example.model.factory.BookFactory;
import com.example.model.json.request.AuthenticationRequest;
import com.example.model.json.response.AuthenticationResponse;
import com.fasterxml.jackson.core.type.TypeReference;
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


        mapper = new ObjectMapper();
        Book book = new Book("TesteTitulo", "Descricao zoada", "RS 135", "Zoado");
//        EntityBook entityBook = BookFactory.createFactoryBook(book);
        jsonInString = mapper.writeValueAsString(book);
        int status = super.mockMvcPerformAuthenticatedPostStatus("/api/books", jsonInString,
                MediaType.APPLICATION_JSON_VALUE, status().isCreated(), this.token);


        String lista = super.mockMvcPerformAuthenticatedGetAll("/api/books", token);

        List<Book> listaBooks = mapper.readValue(lista, mapper.getTypeFactory().constructCollectionType(List.class,Book.class));
        this.listaBook = listaBooks;


    }

    @Test
    public void test1() {
        String a = null;
        Assert.assertNotNull(token);
    }





    @Test
    public void test2getAllBookTest() throws Exception {
//        String lista = null;
        Assert.assertNotNull(listaBook);
    }



//    @Ignore
//    @Test
//    public void test3createBookTest() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Book book = new Book("TesteTitulo", "Descricao zoada", "RS 135", "Zoado");
////        EntityBook entityBook = BookFactory.createFactoryBook(book);
//        String jsonInString = mapper.writeValueAsString(book);
//        int status = super.mockMvcPerformAuthenticatedPostStatus("/api/books", jsonInString,
//                MediaType.APPLICATION_JSON_VALUE, status().isCreated(), this.token);
//        Assert.assertEquals(201, status);
//    }



//    @Ignore
    @Test
    public void test4updateBookTest()throws Exception{
        Book  book = null;
        for(int i = 0; i < listaBook.size();i++){
            if(listaBook.get(i).getTitle().equals("TesteTitulo")){
                book = listaBook.get(i);
            }
        }


        Assert.assertNotNull(book);
        book.setTitle("TesteTituloMudado");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(book);
        int status = super.mockMvcPerformAuthenticatedPutResult("/api/books",
                jsonInString,MediaType.APPLICATION_JSON_VALUE,status().isOk(),token);

        Assert.assertEquals(200,status);
    }

//    @Ignore
    @Test
    public void test5deleteBookTest() throws Exception{
        Book  book = null;
        for(int i = 0; i < this.listaBook.size();i++){
            if(listaBook.get(i).getTitle().equals("TesteTituloMudado")){
                book = listaBook.get(i);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        int status = super.mockMvcPerformAuthenticatedDeleteResult("/api/books","id",book.getId().toString(),
                MediaType.APPLICATION_JSON_VALUE,status().isOk(),token);

        Assert.assertEquals(200,status);
    }


}
