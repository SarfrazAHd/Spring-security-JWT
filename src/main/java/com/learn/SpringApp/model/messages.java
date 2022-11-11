package com.learn.SpringApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learn.SpringApp.util.Severity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class messages {
    private Severity severity;
    private int statusCode;
    private String message;
}
