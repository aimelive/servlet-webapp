package com.aimelive.mywebapp.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.aimelive.mywebapp.dto.CreateFamousDto;
import com.aimelive.mywebapp.models.Famous;

public class FamousRepository {
    Map<UUID, Famous> famousMap = new LinkedHashMap<>();

    public Famous save(CreateFamousDto famousDto) {

        if (findByStartName(famousDto.getStarName()) != null) {
            throw new IllegalArgumentException("Star name already exists!");
        }

        Famous famous = Famous.builder()
                .id(UUID.randomUUID())
                .starName(famousDto.getStarName())
                .age(famousDto.getAge())
                .bio(famousDto.getBio())
                .realName(famousDto.getRealName())
                .createdDate(new Date())
                .build();

        famousMap.put(famous.getId(), famous);

        return findByStartName(famous.getStarName());
    }

    public Famous findByStartName(String startName) {
        for (Famous famous : famousMap.values()) {
            if (famous.getStarName().equalsIgnoreCase(startName)) {
                return famous;
            }
        }
        return null;
    }

    public ArrayList<Famous> findAll() {
        return new ArrayList<>(famousMap.values());
    }

}
