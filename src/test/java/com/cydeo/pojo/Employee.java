package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data  // for implementing getters, setters and more
@JsonIgnoreProperties(ignoreUnknown = true)// allows us to ignore the rest of json keys
public class Employee {

    @JsonProperty("first_name")  // matching json keys with java fields
    public String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private int salary;
}


