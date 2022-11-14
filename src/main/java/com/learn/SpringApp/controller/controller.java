package com.learn.SpringApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.SpringApp.model.User;
import com.learn.SpringApp.model.tokenRequest;
import com.learn.SpringApp.service.Services;
import com.learn.SpringApp.util.RequestUtil;
import com.learn.SpringApp.util.appConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/app/users")
public class controller {
@Autowired
    private Services service;

    @GetMapping(value = appConstants.ALL, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getAllUsers(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        Long StartTime = System.currentTimeMillis();
        Long endTime = null;
        RequestUtil logger = new RequestUtil();
        ResponseEntity response = null;
        String errorMessage = null;
        try {
            response = service.getAll();
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        } finally {
            logger.log(servletRequest, null, StartTime, endTime, response, errorMessage);
        }
        return response;
    }

    @GetMapping(value = "/single/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getOneUser(HttpServletRequest servletRequest, @PathVariable String userId) {
        Long StartTime = System.currentTimeMillis();
        Long endTime = null;
        RequestUtil logger = new RequestUtil();
        ResponseEntity response = null;
        String errorMessage = null;
        try {
            response = service.getOne(userId);
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        } finally {
            logger.log(servletRequest, null, StartTime, endTime, response, errorMessage);
        }
        return response;
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity createUser(HttpServletRequest servletRequest, @RequestBody User user) {
        Long StartTime = System.currentTimeMillis();
        Long endTime = null;
        RequestUtil logger = new RequestUtil();
        ResponseEntity response = null;
        String errorMessage = null;
        try {
            response = service.create(user);
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        } finally {
            logger.log(servletRequest, user.toString(), StartTime, endTime, response, errorMessage);
        }
        return response;
    }

    @PutMapping(value = "/update/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateUser(HttpServletRequest servletRequest, @RequestBody User user, @PathVariable String userId) {
        Long StartTime = System.currentTimeMillis();
        Long endTime = null;
        RequestUtil logger = new RequestUtil();
        ResponseEntity response = null;
        String errorMessage = null;
        try {
            response = service.update(user, userId);
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        } finally {
            logger.log(servletRequest, user.toString(), StartTime, endTime, response, errorMessage);
        }
        return response;
    }

    @DeleteMapping(value = "/delete/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity deleteUser(HttpServletRequest servletRequest, @PathVariable(value = "userId") String id) {

        Long StartTime = System.currentTimeMillis();
        Long endTime = null;
        RequestUtil logger = new RequestUtil();
        ResponseEntity response = null;
        String errorMessage = null;
        try {
            response = service.delete(id);
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        } finally {
            logger.log(servletRequest, null, StartTime, endTime, response, errorMessage);
        }
        return response;
    }

    @GetMapping(value = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> searchUser(HttpServletRequest servletRequest, @RequestParam("q") String searchText) throws JsonProcessingException {
        Long StartTime = System.currentTimeMillis();
        Long endTime = null;
        RequestUtil logger = new RequestUtil();
        ResponseEntity response = null;
        String errorMessage = null;
        try {
            response = service.search(searchText);
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        } finally {
            logger.log(servletRequest, null, StartTime, endTime, response, errorMessage);
        }
        return response;
    }

    @PostMapping(value = "/generate/token",consumes={ MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces={ MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity token(@RequestBody tokenRequest tokenRequest){
        String token=null;
        try{
            token = service.getToken(tokenRequest);
        }catch(Exception e){
            token=e.getMessage();
        }
        return ResponseEntity.ok(token);
    }
}
