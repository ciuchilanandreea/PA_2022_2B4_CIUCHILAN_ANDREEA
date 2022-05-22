package org.example;

import org.example.annotation.Test;

class ExampleTest {

    @Test
    public static void methodA() {
    }

    public static void methodB() {
    }

    @Test
    public static void TestRunTimeException() throws Exception {
        throw new Exception("Run time exception! Ooops...");
    }

    public static void methodZ() {
    }

    @Test
    public static void TestMethod() {
    }

    public static void methodW() {
    }

    @Test
    public static void TestRunTimeException2() {
        throw new RuntimeException("Crash");
    }

    public static void methodP() {
    }
}

