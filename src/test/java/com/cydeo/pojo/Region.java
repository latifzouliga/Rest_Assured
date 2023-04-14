package com.cydeo.pojo;

import com.cydeo.pojo.Link;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {

    @JsonProperty("region_name")
    private String regionName;
    @JsonProperty("region_id")
    private int regionId;

    private List<Link> links;

}
