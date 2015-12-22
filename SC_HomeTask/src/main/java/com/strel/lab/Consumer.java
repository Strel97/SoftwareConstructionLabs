package com.strel.lab;

import java.util.Random;

/**
 * Created by Сергей on 21.12.2015.
 */
public class Consumer extends Thread {

    private final static int TEXT_CNT = 5;

    private TextGenerator text;
    private int num;


    public Consumer(TextGenerator text, int num) {
        this.text = text;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < TEXT_CNT; i++) {
            System.out.println("Consumer #" + num + " got generated text " + text.getText());
        }
    }
}
