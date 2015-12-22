package com.strel.lab;

/**
 * Created by Сергей on 21.12.2015.
 */
public class TextGenerator {

    private Text text;
    private boolean available = false;


    public synchronized void generate(String title, int sectionCnt, int wordsCnt, int lineCnt) {
        while (available == true) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        text = new Text(title, sectionCnt, wordsCnt, lineCnt);
        available = true;
        notifyAll();
    }

    public synchronized Text getText() {
        while (available == false) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        available = false;
        notifyAll();
        return text;
    }
}
