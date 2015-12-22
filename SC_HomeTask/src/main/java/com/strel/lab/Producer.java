package com.strel.lab;

import java.util.Random;

/**
 * Created by Сергей on 21.12.2015.
 */
public class Producer extends Thread {

    private final static String[] TITLES = {"title1", "title2", "title3", "title4", "title5"};
    private final static int TEXT_CNT = 5;
    private final static Random RAND = new Random();

    private TextGenerator text;
    private int num;


    public Producer(TextGenerator text, int num) {
        this.text = text;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < TEXT_CNT; i++) {
            text.generate(
                    TITLES[i],
                    RAND.nextInt(10) + 1,
                    RAND.nextInt(500) + 1,
                    RAND.nextInt(100) + 1
            );
            System.out.println("Producer #" + num + " has generated text '" + TITLES[i] + "'");

            try {
              sleep(RAND.nextInt(100) + 1);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
