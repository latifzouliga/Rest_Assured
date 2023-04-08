package com.cydeo.pojo.region;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {

    private String rel;
    private String href;
}
