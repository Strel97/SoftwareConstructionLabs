package com.strel.lab.bonus;

import java.util.Random;

/**
 * Created by Сергей on 06.10.2015.
 */
public class Task {

    private static final int ARR_LENGTH = 30;

    private static final int ASCII_FIRST_CHAR = 33;
    private static final int ASCII_UPPER_BOUND = 92;


    public static void main(String[] args) {
        char[] characters = generateArray(ARR_LENGTH);

        System.out.println("Generated array of chars: " + new String(characters));
        System.out.println("Sum of digits in array: " + getSumOfDigits(characters));
        System.out.println("Array with capitalized letters: " + new String(capitalizeSomeLetters(characters)));
        System.out.println("Replacing 'ov' with 'enko' in surnames: " +
                "\n\tINITIAL [ Petersov, ovBiley, Murphy, Coovk, Coov ]" +
                "\n\tAFTER [" + processSurname(" Petersov, ovBiley, Murphy, Coovk, Coov ") + "]");
    }


    public static String processSurname(String input) {
        return input.replaceAll("((\\w|\\d)+)(ov)(\\W)", "$1enko$4");
    }

    private static char[] capitalizeSomeLetters(char[] arr) {
        char[] new_arr = arr.clone();
        for (int i = 1; i < new_arr.length; i++) {
            if (Character.isLetter(new_arr[i]) && Character.isLetter(new_arr[i-1])) {
                new_arr[i] = Character.toTitleCase(new_arr[i]);
                new_arr[i-1] = Character.toTitleCase(new_arr[i-1]);
            }
        }

        return new_arr;
    }

    private static int getSumOfDigits(char[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i]))
                sum += Character.digit(arr[i], 10);
        }

        return sum;
    }

    private static char[] generateArray(int length) {
        char[] arr = new char[length];
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            arr[i] = (char) (rand.nextInt(ASCII_UPPER_BOUND) + ASCII_FIRST_CHAR);
        }

        return arr;
    }
}
