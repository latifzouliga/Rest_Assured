
package com.cydeo.pojo.ready;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "students"
})

public class Students {

    @JsonProperty("students")
    private List<Student> students;

    @JsonProperty("students")
    public List<Student> getStudents() {
        return students;
    }

    @JsonProperty("students")
    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
