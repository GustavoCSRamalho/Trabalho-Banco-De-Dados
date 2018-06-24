package com.example.service.impl;


import com.example.domain.model.informacoes.Information;
import com.example.repository.InformationRepository;
import com.example.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("informationService")
public class InformationServiceImpl implements InformationService {


    @Qualifier("informationRepository")
    @Autowired
    private InformationRepository informationRepository;

    @Override
    public void delete(long id) {
        Information bookD = informationRepository.findOne(id);
        informationRepository.delete(bookD);
    }

    @Override
    public void update(Information information) {
        Information informationUp = informationRepository.findOne(information.getId());
        informationUp.setGender(information.getGender());
        informationUp.setDescription(information.getDescription());
        informationUp.setRace(information.getRace());
        informationUp.setBear(information.getBear());
//        informationUp.setImage(information.getImage());
        informationUp.setLocal(information.getLocal());
        informationUp.setName(information.getName());
        informationRepository.save(informationUp);
    }

    @Override
    public void create(Information information) {
        informationRepository.save(information);
    }

    @Override
    public List<Information> findAll() {
        return informationRepository.findAll();
    }

    @Override
    public Information findById(long id) {
        return informationRepository.findOne(id);
    }
}
