package com.strel.lab;

/**
 * Created by Сергей on 21.12.2015.
 */
public class Text {

    private String  title;
    private int     sectionCnt;
    private int     wordsCnt;
    private int     lineCnt;


    public Text(String title, int sectionCnt, int wordsCnt, int lineCnt) {
        this.title = title;
        this.sectionCnt = sectionCnt;
        this.wordsCnt = wordsCnt;
        this.lineCnt = lineCnt;
    }

    public String getTitle() {
        return title;
    }

    public int getSectionCnt() {
        return sectionCnt;
    }

    public int getWordsCnt() {
        return wordsCnt;
    }

    public int getLineCnt() {
        return lineCnt;
    }

    @Override
    public String toString() {
        return "TEXT [ " +
                "title='" + title + '\'' +
                ", sectionCnt=" + sectionCnt +
                ", wordsCnt=" + wordsCnt +
                ", lineCnt=" + lineCnt +
                " ]";
    }
}
