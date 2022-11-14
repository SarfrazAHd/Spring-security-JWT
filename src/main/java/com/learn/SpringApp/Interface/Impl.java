package com.learn.SpringApp.Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.SpringApp.model.User;
import com.learn.SpringApp.util.appConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class Impl implements Interface {
    Logger logger = LoggerFactory.getLogger(Impl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getAll() {
        ResponseEntity response = null;
        try {
            response = restTemplate.getForEntity(appConstants.BASE_URL, String.class);
            logger.info("get All");
        } catch (Exception e) {
            e.getMessage();
        }
        return response.getBody().toString();
    }

    @Override
    public String getOne(String id) {
        ResponseEntity response = null;
        try {
            response = restTemplate.getForEntity(appConstants.BASE_URL + id, String.class);
            logger.info("get one");
        } catch (Exception e) {
            e.getMessage();
        }
        return response.getBody().toString();
    }

    @Override
    public ResponseEntity create(User user) {
        ResponseEntity response = null;
        try {
            response = restTemplate.postForEntity(appConstants.POST_URL, user, String.class);
            logger.info("Create");
        } catch (Exception e) {
            e.getMessage();
        }
        return response;
    }

    @Override
    public void udpate(User user, String id) {
        try {
            restTemplate.put(appConstants.BASE_URL + id, user);
            logger.info("successfully updated");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(String id) {
        try {
            restTemplate.exchange(appConstants.BASE_URL + id, HttpMethod.DELETE, null, String.class);
            logger.info("delete");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public String search(String searchText) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = getAll();
        } catch (Exception e) {
            e.getMessage();
        }
        JsonNode node = mapper.readTree(result);
        List<JsonNode> filteredUser = new ArrayList<>();
        for (int i = 0; i < node.size(); i++) {
            String name = node.get(i).get("name").textValue();
            String username = node.get(i).get("username").textValue();
            if (name.contains(searchText) || username.contains(searchText)) {
                filteredUser.add(node.get(i));
            }
        }
        if (filteredUser.size() == 0) {
            throw new NullPointerException();
        }
        return filteredUser.toString();
    }
}
