package com.cydeo.day11_JUnit5_DDT;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P06_MethodSourceTest {


    // this static method is returning list of string
    // but we need to get the values individually that is why we need to choose it as string in this case
    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name){

        System.out.println(name);

    }


    @ParameterizedTest
    @MethodSource("getExelData")
    public void test2(Map<String, String> userInfo){
        System.out.println(userInfo);
        System.out.println("Name = " + userInfo.get("Name"));
        System.out.println("Role = " + userInfo.get("Role"));
        System.out.println("Email = " + userInfo.get("Email"));
        System.out.println("Password = " + userInfo.get("Password"));
        System.out.println("===================================\n");

    }

    public static List<Map<String,String>> getExelData(){
        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/Library.xlsx","Library1");
        return excelUtil.getDataList();
    }



    public static List<String> getNames(){
        List<String> nameList = Arrays.asList(
                "Mike", "Rose", "Steven", "TJ", "Harold", "Severus", "Sherlock"
        );
        return nameList;

        /*  -Can we read data from database and assign the list ?
                -Create conn, run query , store them and feed to Parameterized ?
            -Can we get data from 3rd party APIs ( that we consume to get data and use into our API)
                -use Java Knowledge and RestAssured
           - this makes method source more powerful than others.
         */
    }
}
