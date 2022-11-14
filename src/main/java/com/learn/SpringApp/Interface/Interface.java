package com.learn.SpringApp.Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.SpringApp.model.User;
import org.springframework.http.ResponseEntity;

public interface Interface{

    String getAll();
    String getOne(String id);
     ResponseEntity create(User usr);
    void udpate(User use, String id);
    void delete(String id);
    String search(String searchText) throws JsonProcessingException;
}
