
package com.cydeo.pojo.ready;



import com.cydeo.pojo.Company;
import com.cydeo.pojo.Contact;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "studentId",
    "firstName",
    "lastName",
    "batch",
    "joinDate",
    "birthDate",
    "password",
    "subject",
    "gender",
    "admissionNo",
    "major",
    "section",
    "contact",
    "company"
})
@Data
public class Student {


    private Integer studentId;
    private String firstName;
    private String lastName;
    private Integer batch;
    private String joinDate;
    private String birthDate;
    private String password;
    private String subject;
    private String gender;
    private String admissionNo;
    private String major;
    private String section;
    private Contact contact;
    private Company company;


}
