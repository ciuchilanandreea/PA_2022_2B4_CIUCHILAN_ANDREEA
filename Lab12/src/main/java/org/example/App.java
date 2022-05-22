package org.example;

import org.example.annotation.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;


/**
 * <p>Path-ul catre fisier este hardcodat</p>
 */
public class App {
    public static void main(String[] args) throws Exception {
        int passed = 0, failed = 0;
        System.out.println("Begin...");
        System.out.println("Tested on App.class in: D:\\_ user ecaaa\\Desktop\\ClassTest\\Client ");
        String pathToClass = "D:\\_ user Andreea\\Desktop\\ClassTest\\Client";
        ClassLoading classLoading = new ClassLoading();
        File filePath = new File(pathToClass);
        if (filePath.exists()) {
            URL toURL = filePath.toURI().toURL();

        }
        for (Method methodInClass : classLoading.loadClass("org.example.ExampleTest").getMethods()) {
            if (methodInClass.isAnnotationPresent(Test.class)) {
                try {
                    methodInClass.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n", methodInClass, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d\nFailed: %d%n\n", passed, failed);
        System.out.println("End...");
    }
}
