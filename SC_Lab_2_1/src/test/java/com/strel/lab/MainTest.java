package com.strel.lab;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Сергей on 26.11.2015.
 */
public class MainTest {
    public static void main(String[] args) {
        Album justice = new Album();

        /**
         * First Task
         */
        justice.load("SC_Lab_2_1/src/main/resources/albums.properties");

        System.out.println("Group: " + justice.getGroup());
        System.out.println("Name: " + justice.getName());
        System.out.println("Year: " + justice.getYear());

        HashSet<Track> tracklist = justice.getTracklist();

        System.out.println("Track List: ");
        tracklist.forEach(track -> System.out.println("\t" + track));
        System.out.println();


        /**
         * Second Task
         */
        Scanner in = new Scanner( System.in );
        String  name = "";

        while (!"enough".equals(name)) {
            System.out.println();
            System.out.print("Please enter track name, that you want to check: ");
            name = in.nextLine();

            System.out.println("Does Justice contains '" + name + "' track?  Answer: "
                    + justice.containsTrack(name, "Metallica"));
        }


        /**
         * Third Task
         */
        ArrayDeque<Track> tracks = justice.moveTracks(2);

        System.out.println();
        System.out.print("Tracks that have two words in name: [ ");
        tracks.forEach( track -> System.out.print(track + " ") );
        System.out.println("]");


        /**
         * Fourth Task
         */
        try {
            PrintWriter writer = new PrintWriter("SC_Lab_2_1/src/main/resources/out.txt");

            int len = tracks.size();
            for (int i = 0; i < len; i++) {
                writer.append(tracks.poll().toString());
                writer.append("\n");
            }

            writer.close();
        }
        catch (FileNotFoundException ex) {
            System.err.println("ERR: File not found!");
        }
    }
}
