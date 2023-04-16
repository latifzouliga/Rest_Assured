package com.cydeo.day11_JUnit5_DDT;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

class P07_ExelUtilPractice {


    @Test
    void test1(){

        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/Library.xlsx","Library1");

        System.out.println("excelUtil.rowCount() = " + excelUtil.rowCount());
        System.out.println("excelUtil.columnCount() = " + excelUtil.columnCount());


        for (Map<String, String> each : excelUtil.getDataList()) {
            System.out.println(each);
        }
    }
}
