package com.example;

public class helloworld {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        int aux;

        n *= 3;
        aux = Integer.parseInt(0b10101);
        n += aux;
        aux = Integer.parseInt(0xFF);
        n+= aux;
        n *= 6;

        while(n > 9)
        {
            aux = 0;
            while(n > 0)
            {
                aux = aux + n%10;
                n = n/10;
            }
            n = aux;
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
}
