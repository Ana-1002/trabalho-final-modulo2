package com.dbc.service;

import com.dbc.entities.Category;
import com.dbc.entities.Request;
import com.dbc.repository.RequestRepository;

import java.sql.SQLException;

public class RequestService {

    private RequestRepository requestRepository;

    public RequestService() {
        this.requestRepository = new RequestRepository();
    }

    public void add(Request request) {
        try {
            Request addedRequest = requestRepository.add(request);
            System.out.println("Request adicionado com sucesso! " + addedRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        try {
            boolean isRemoved = requestRepository.remove(id);
            System.out.println("removido? " + isRemoved + "| com id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, Request request) {
        try {
            boolean isUpdated = requestRepository.update(id, request);
            System.out.println("editado? " + isUpdated + "| com id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void list() {
        try {
            requestRepository.list().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
