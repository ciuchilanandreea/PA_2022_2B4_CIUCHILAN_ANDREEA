/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_java;

/**
 *
 * @author Andreea
 */
public class Lab1_java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World!"); 
        String language[]={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);
        System.out.println(n);
        n=n*3;
        System.out.println(n);
        int number1 = Integer.parseInt("10101", 2);
        n=n+number1;
        System.out.println(n);
        
        int number2 = Integer.parseInt("FF",16);
        n=n+number2;
        System.out.println(n);
        
        n=n*6;
        System.out.println(n);
        
        int result;
        while(n>9)
        {
          int sum=0;
          result=n;
          while(result!=0)
          {
              sum=sum+result%10;
              result=result/10;
          }
          n=sum;
          System.out.println(n);
        }
      
        System.out.println("Willy-nilly, this semester I will learn " + language[n]);
    }
    
}
