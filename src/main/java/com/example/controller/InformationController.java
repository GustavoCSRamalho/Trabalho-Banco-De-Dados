package com.example.controller;


import com.example.domain.entity.EntityInformation;
import com.example.domain.model.book.Book;
import com.example.domain.model.informacoes.Information;
import com.example.domain.model.informacoes.InformationRequest;
import com.example.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/api")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private Information information;

    //    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(value = {"/send"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody InformationRequest informationRequest) {
//        Information information = null;
//        information = new Information(informationRequest);
        information.setId(informationRequest.getId());
        information.setName(informationRequest.getName());
        information.setDescription(informationRequest.getDescription());
        information.setLocal(informationRequest.getLocal());
        information.setBear(informationRequest.getBear());
        information.setRace(informationRequest.getRace());
        information.setGender(informationRequest.getGender());
//        System.out.println(informationRequest.getDescription());
//        System.out.println(informationRequest.getFile().getName());
        System.out.println(information);
        try {

//            File file = informationRequest.getFile();
//            byte[] bytes = new byte[(int) file.length()];
//            FileInputStream fis = new FileInputStream(file);
//            fis.read(bytes); //read file into bytes[]
//            fis.close();
//            information = new Information(informationRequest.getName(), informationRequest.getDescription(),
//                    informationRequest.getLocal(), informationRequest.getRace(), informationRequest.getGender(),
//                    informationRequest.getBear(), bytes.toString());

            informationService.create(information);
            // Change for information
            return new ResponseEntity(information, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(information, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = {"/upload"}, method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam(value = "file") MultipartFile file) throws IOException {


        System.out.println(file.getContentType());
        File fileNovo = new File("/home/gustavo/"+file.getOriginalFilename());
        file.transferTo(fileNovo);

        String base64Image = "";
        try (FileInputStream imageInFile = new FileInputStream(fileNovo)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) fileNovo.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        System.out.println("Base 64 : "+base64Image);
        information.setFile("data:"+file.getContentType()+";base64,"+base64Image);
//        System.out.println(file.getBytes());
        try {
            return new ResponseEntity(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.CONFLICT);
        }
    }

    //    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping(value = {"/animals"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Information>> findAll() {
//        List<Book> lista = null;
        List<Information> lista = null;
        try {
            lista = informationService.findAll();
//            lista.forEach( u -> System.out.println(u.getName()));
            // Change for information
            return new ResponseEntity<List<Information>>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Information>>(lista, HttpStatus.NO_CONTENT);
        }
    }

    //    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @RequestMapping(value = {"/animal/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<Information> findById(@PathVariable(value = "id") Long id) {
        Information information = null;
//        EntityInformation entityInformation = null;
        try {
//            book = bookService.findById(id);
            information = informationService.findById(id);

//            entityBook = BookFactory.createFactoryBook(book);
            return new ResponseEntity<Information>(information, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Information>(information, HttpStatus.NO_CONTENT);
        }
    }

    //Add Later!


//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @RequestMapping(value = {"/books"}, method = RequestMethod.DELETE)
//    public ResponseEntity<?> delete(@RequestParam(name = "id") long book) {
//        try {
//            bookService.delete(book);
//            return new ResponseEntity(book, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(book, HttpStatus.EXPECTATION_FAILED);
//        }
//    }


    //Add later

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @RequestMapping(value = {"/books"}, method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@RequestBody Book book) {
//
//        try {
//            bookService.update(book);
//            return new ResponseEntity(book, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(book, HttpStatus.EXPECTATION_FAILED);
//        }
//    }
}
