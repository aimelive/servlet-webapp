package com.aimelive.mywebapp.controllers;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aimelive.mywebapp.dto.CreateFamousDto;
import com.aimelive.mywebapp.models.Famous;
import com.aimelive.mywebapp.services.FamousService;
import com.aimelive.mywebapp.utils.HttpResponse;
import com.google.gson.Gson;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@WebServlet("/api/famous")
public class FamousController extends HttpServlet {
    private FamousService famousService = new FamousService();

    CreateFamousDto getBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        CreateFamousDto createFamousDto = gson.fromJson(body, CreateFamousDto.class);

        Set<ConstraintViolation<CreateFamousDto>> violations = validator.validate(createFamousDto);

        if (!violations.isEmpty()) {
            HttpResponse response = new HttpResponse(resp, 400);

            String errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.joining(", "));

            response.send(errorMessage);

            return null;
        }
        return createFamousDto;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateFamousDto famousDto = getBody(req, resp);
        if (famousDto == null) {
            return;
        }
        HttpResponse response = new HttpResponse(resp, 201);

        try {
            Famous createdFamous = famousService.create(famousDto);
            response.send("Famous added successfully!", createdFamous);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.send(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpResponse response = new HttpResponse(resp);
        response.send("Famous retrieved successfully", famousService.getAll());
    }
}
