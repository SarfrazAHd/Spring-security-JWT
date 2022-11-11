package com.learn.SpringApp.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Geo implements Serializable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
/*    @JsonSerialize(using = StringSerializer.class)
    private Geo geo;*/
}