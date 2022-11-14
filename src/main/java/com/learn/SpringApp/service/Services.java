package com.learn.SpringApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.SpringApp.Interface.Interface;
import com.learn.SpringApp.Interface.UserDetailServiceImpl;
import com.learn.SpringApp.model.User;
import com.learn.SpringApp.model.messages;
import com.learn.SpringApp.model.TokenRequest;
import com.learn.SpringApp.model.TokenResponse;
import com.learn.SpringApp.util.JwtTokenUtil;
import com.learn.SpringApp.util.Severity;
import com.learn.SpringApp.util.appConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Services {


    Logger log = LoggerFactory.getLogger(this.getClass());

    private Interface intf;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl useDetailService;

    @Autowired
    private JwtTokenUtil jwt;

    @Autowired
    Services(Interface intf) {
        this.intf = intf;
    }

    public ResponseEntity getAll() {
        String result = null;
        try {
            result = intf.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new messages(Severity.EXCEPTION, appConstants.CODE_500, appConstants.INTERNAL_SERVER_EROOR + " - " + e.getMessage()));
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity getOne(String id) {
        String result = null;
        try {
            result = intf.getOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new messages(Severity.EXCEPTION, appConstants.CODE_500, appConstants.INTERNAL_SERVER_EROOR + " - " + e.getMessage()));
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity create(User user) {
        messages msg = null;
        ResponseEntity resp;
        try {
            resp = intf.create(user);
            if (resp.getStatusCode().is2xxSuccessful()) {
                msg = new messages(Severity.INFO, appConstants.OK_200, appConstants.RESOURCE_CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new messages(Severity.EXCEPTION, appConstants.CODE_500, appConstants.INTERNAL_SERVER_EROOR + " - " + e.getMessage()));
        }
        return ResponseEntity.ok(msg);
    }

    public ResponseEntity update(User user, String id) {
        messages msg = null;
        try {
            intf.udpate(user, id);
            msg = new messages(Severity.INFO, appConstants.OK_200, appConstants.RESOURCE_UPDATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new messages(Severity.EXCEPTION, appConstants.CODE_500, appConstants.INTERNAL_SERVER_EROOR + " - " + e.getMessage()));
        }
        return ResponseEntity.ok(msg);
    }

    public ResponseEntity delete(String id) {
        messages msg = null;
        try {
            intf.delete(id);
            msg = new messages(Severity.INFO, appConstants.OK_200, appConstants.RESOURCE_DELETED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new messages(Severity.EXCEPTION, appConstants.CODE_500, appConstants.INTERNAL_SERVER_EROOR + " - " + e.getMessage()));
        }
        return ResponseEntity.ok(msg);
    }

    public ResponseEntity search(String searchText) throws JsonProcessingException {
        String result = null;
        try {
            if (searchText.isEmpty()) {
                throw new NullPointerException();
            }
            result = intf.search(searchText);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new messages(Severity.INFO, appConstants.CODE_404, appConstants.NO_MATCHES_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new messages(Severity.EXCEPTION, appConstants.CODE_500, appConstants.INTERNAL_SERVER_EROOR + " - " + e.getMessage()));
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity getToken(TokenRequest req) {
        String token = null;
        TokenResponse response = new TokenResponse();
        try {
            authenticate(req.getClientId(), req.getClientSecret());
            UserDetails userDetails = useDetailService.loadUserByUsername(req.getClientId());
            token = jwt.generateToken(userDetails);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new messages(Severity.WARNING, appConstants.CODE_400, appConstants.INVALID_CLIENTID_SECRET + " - " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new messages(Severity.EXCEPTION, appConstants.CODE_500, appConstants.TOKEN_GENERATION_ERROR + " - " + e.getMessage()));
        }
        response.setToken(token);
        response.setType("Bearer");
        response.setExpiresIn(jwt.getExpirationDateFromToken(token));
        return ResponseEntity.ok(response);
    }

    void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}