package com.learn.SpringApp.model;

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
    /*private Geo geo;*/
}