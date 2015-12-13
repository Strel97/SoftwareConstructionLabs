package com.strel.lab;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

/**
 * Created by Сергей on 06.12.2015.
 *
 * Variant          : 13
 * f (x)            : 3 - x ^ 3 + sin (pi * x / 2)
 * filter           : On condition |pfx| < tol
 * txt format       : Level, Cause, Message, Value, y, iteration num
 * XML parameters   : Встановити фіксований розмір файлу, при переповнені
 *                    записувати в новий файл за шаблоном lab2.1.1.log, lab2.1.2.log
 */
public class Main {

    private static Logger in_out_log = Logger.getLogger("logger 1");
    private static Logger xml_log = Logger.getLogger("logger 2");
    private static Logger pfx_tol_log = Logger.getLogger("logger 3");


    /**
     * Calculates the value of given function
     * at a particular value.
     *
     * @param x parameter of function
     * @return  value of function at a given parameter
     */
    public static double func(double x) {
        return 3 - Math.pow(x, 3) + Math.sin(Math.PI * x / 2);
    }


    /**
     * Calculates the value of derivative of given
     * function at a particular value.
     *
     * @param x parameter of function
     * @return  value of function at a given point
     */
    public static double dFunc(double x) {
        return 1/2 * Math.PI * Math.cos(Math.PI * x / 2) - 3 * Math.pow(x, 2);
    }


    /**
     * Evaluates the root of algebraic equation by
     * method of tangent lines.
     *
     * @param x0    the initial approximation to root
     * @param N     number of allowed iterations
     * @param tol   accuracy of calculations
     *
     * @return      root of equation
     */
    public static double newton(double x0,  int N, double tol) {
        double x = x0;
        double n = 0;

        while (n <= N) {
            double fx = func(x);
            if (fx < tol) {
                xml_log.log(Level.INFO, "Y = {0} [--%--] n = {1}", new Object[] {x, n});
                return x;
            }

            double pfx = dFunc(x);
            if (Math.abs(pfx) < tol) {
                System.err.println("== SYSTEM: df(x) is small: giving up!");
                xml_log.log(Level.INFO, "Y = {0} [--%--] n = {1}", new Object[]{x, n});
                return x;
            }

            pfx_tol_log.log(Level.INFO, "|pfx| = {0} AND tol = {1}", new Object[] {pfx, tol});
            x = x - fx / pfx;
            n++;
        }

        xml_log.log(Level.INFO, "Y = {0} [--%--] n = {1}", new Object[]{x, n});
        return x;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        try {
            // Configuration of first logger
            FileHandler fileHandler = new FileHandler("first_log.log");
            fileHandler.setFormatter(new MyFormatter());

            in_out_log.addHandler(new ConsoleHandler());
            in_out_log.addHandler(fileHandler);


            // Configuration of second logger
            xml_log.addHandler(new FileHandler("lab2.1.%g.xml", 1024 * 1024, 10, true));

            // Config third logger
            pfx_tol_log.setFilter(new MyFilter());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


        System.out.println("== SYSTEM: Algebraic equation evaluation system was started...");

        double x0 = 0;
        boolean need_user_in = true;
        while(need_user_in) {
            System.out.println("== SYSTEM: Please input the initial approximation to root value");
            System.out.print("== USER: ");
            x0 = in.nextDouble();

            if (func(x0) * dFunc(x0) > 0)
                need_user_in = false;
            else
                xml_log.warning("== SYSTEM: Convergence of method.");
        }
        in_out_log.log(Level.INFO, "User input: initial approximation value = {0}", x0);

        System.out.println("== SYSTEM: Please input the number of allowed iterations");
        System.out.print("== USER: ");
        int N = in.nextInt();
        in_out_log.log(Level.INFO, "User input: the number of allowed iterations = {0}", N);

        System.out.println("== SYSTEM: Please input the value of accuracy of calculations");
        System.out.print("== USER: ");
        double tol = in.nextDouble();
        in_out_log.log(Level.INFO, "User input: the value of accuracy of calculations = {0}", tol);

        double result = newton(x0, N, tol);
        System.out.println("== SYSTEM: The root of equation = " + result);
        in_out_log.log(Level.INFO, "System output: the result of calculations = {0}", result);
    }
}
