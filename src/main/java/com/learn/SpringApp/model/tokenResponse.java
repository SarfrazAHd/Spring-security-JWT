package com.learn.SpringApp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tokenResponse {
    private String token;
    private String type;
    private long expiresIn;
}
