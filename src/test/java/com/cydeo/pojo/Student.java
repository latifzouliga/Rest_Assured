package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private String firstName;
    private int batch;
    private String major;
    private Contact contact;
    private Company company;

}

