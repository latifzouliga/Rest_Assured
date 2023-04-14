package com.cydeo.day11_JUnit5_DDT;

import org.junit.jupiter.api.*;

public class P01_jUnit5AnnotationsLifeCycle {


    @BeforeAll
    static void init(){
        System.out.println("------------BeforeAll is running-----------");
    }
    @BeforeEach
    void beforeEach(){
        System.out.println("--------------BeforeEach is running-----------");
    }
    @AfterEach
    void afterEach(){
        System.out.println("--------------------AfterEach is running-----------");
    }
    @Test
    void test1() {
        System.out.println("----------------Test 1 is running--------------");
    }

    @Test
    void test2() {
        System.out.println("----------------Test 2 is running--------------");
    }

    @AfterAll
    static void destroy(){
        System.out.println("------------AfterAll is running-----------");
    }



}
