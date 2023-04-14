package com.cydeo.day11_JUnit5_DDT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class P02_JUnit5Assertions {

    /**
     * Hard Assert -->
     * Test Execution will be aborted if the Assert condition is not met
     * Rest of the execution will stop
     */

    @Test
    void hardAssertTest() {
        assertEquals(10, 5 + 5);
        System.out.println("----First Assert is DONE");
    }

    @Test
    @DisplayName("JUnit 5 Soft Assertion is implemented")
    void assertAllTest(){
        assertAll( "Learning Soft Assert",
                ()-> assertEquals(10, 5+5),
                ()-> assertEquals(3 , 5-2),
                ()-> assertEquals(10 , 5+4)
        );
    }
}
