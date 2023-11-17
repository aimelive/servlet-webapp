package com.aimelive.mywebapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aimelive.mywebapp.utils.HttpResponse;

@WebServlet("/api/hello")
public class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpResponse apiResponse = new HttpResponse(response, 200);
        apiResponse.send("Welcome to our Famous Api.");
    }
}