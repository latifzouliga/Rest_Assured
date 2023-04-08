package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"constructorId",
"url",
"name",
"nationality"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructorPOJO {

@JsonProperty("constructorId")
public String constructorId;
@JsonProperty("url")
public String url;
@JsonProperty("name")
public String name;
@JsonProperty("nationality")
public String nationality;

}