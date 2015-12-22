package com.strel.lab;

/**
 * Created by Сергей on 21.12.2015.
 */
public class Test {
    public static void main(String[] args) {
        TextGenerator text = new TextGenerator();
        Producer p1 = new Producer(text, 1);
        Consumer c1 = new Consumer(text, 1);

        p1.start();
        c1.start();
    }
}
