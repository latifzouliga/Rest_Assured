package com.cydeo.pojo;

import java.lang.reflect.Constructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructorTable {
    private ConstructorPOJO constructorPOJOS;
    private String constructorStandings;

}
