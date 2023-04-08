package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MRData {
    private String limit;
    private String total;
    private ConstructorTable constructorTable;
}
