package com.metallica.killemall;


import java.awt.*;
import java.util.Arrays;

/**
 * Created by Сергей on 08.09.2015.
 */
public class Matrix {

    private int width;
    private int height;

    private int[][] data;


    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new int[height][width];
    }

    public Matrix(int[][] data, int width, int height) {
        this(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    public Point getMinPos() {
        Point min_pos = new Point();
        int min = data[0][0];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (data[i][j] < min) {
                    min = data[i][j];
                    min_pos.y = i;
                    min_pos.x = j;
                }
            }
        }

        return min_pos;
    }

    public Matrix getNewWithoutMin() {
        int[][] new_data = copy(data, width, height);
        Point min_pos = getMinPos();

        new_data = deleteCol(new_data, min_pos.x);
        new_data = deleteRow(new_data, min_pos.y);

        return new Matrix(new_data, width-1, height-1);
    }

    private int[][] deleteRow(int[][] arr, int row) {
        for (int i = row; i < height-1; i++) {
            arr[i] = arr[i+1];
        }

        return arr;
    }

    private int[][] deleteCol(int[][] arr, int col) {
        for (int i = 0; i < height; i++) {
            for (int j = col; j < width-1; j++) {
                arr[i][j] = arr[i][j+1];
            }
        }

        return arr;
    }

    private int[][] copy(int[][] arr, int width, int height) {
        int[][] new_arr = new int[height][width];
        for (int i = 0; i < height; i++) {
            new_arr[i] = Arrays.copyOf(arr[i], width);
        }

        return new_arr;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix = [\n");
        for (int[] line : data) {
            sb.append("\t");
            for (int num : line) {
                sb.append(num + " ");
            }
            sb.append("\n");
        }
        sb.append("]");

        return sb.toString();
    }


    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 4},
                {2, 3, 0},
                {2, 3, 5},
                {5, 4, 1}
        };

        Matrix m = new Matrix(arr, 3, 4);
        System.out.println(m);

        System.out.println(m.getNewWithoutMin());
    }
}
