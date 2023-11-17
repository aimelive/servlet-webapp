package com.aimelive.mywebapp.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ResponseData {
    private int statusCode;
    private String message;
    private Object data;
}

@Data
@AllArgsConstructor
public class HttpResponse {
    private HttpServletResponse response;
    private int statusCode;

    public HttpResponse(HttpServletResponse response) {
        this.response = response;
        this.statusCode = 200;
    }

    public void send(String message, Object... data) throws IOException {
        response.setContentType("application/json");

        response.setStatus(statusCode);

        Gson gson = new Gson();

        ResponseData responseData = new ResponseData(statusCode, message, data.length > 0 ? data[0] : null);

        String json = gson.toJson(responseData);

        OutputStream out = response.getOutputStream();

        out.write(json.getBytes());
        out.flush();
    }
}
