package com.learn.SpringApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tokenRequest {
    private String clientId;
    private String clientSecret;
}
