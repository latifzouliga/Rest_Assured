package com.cydeo.pojo.region;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(
        ignoreUnknown = true
        //value = "links",
        //allowGetters = true
)
public class Items {

    @JsonProperty("region_id")
    private int regionId;
    @JsonProperty("region_name")
    private String regionName;
   private List<Links> links;

}
