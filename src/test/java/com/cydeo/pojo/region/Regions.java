package com.cydeo.pojo.region;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Regions {

    private String hasMore;
    private String limit;
    private int count;
    private List<Items> items;
}
