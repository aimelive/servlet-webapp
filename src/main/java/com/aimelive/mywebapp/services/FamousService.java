package com.aimelive.mywebapp.services;

import java.util.ArrayList;

import com.aimelive.mywebapp.dto.CreateFamousDto;
import com.aimelive.mywebapp.models.Famous;
import com.aimelive.mywebapp.repositories.FamousRepository;

public class FamousService {
    FamousRepository famousRepository = new FamousRepository();

    public Famous create(CreateFamousDto dto) {
        return famousRepository.save(dto);
    }

    public ArrayList<Famous> getAll() {
        return famousRepository.findAll();
    }

}
