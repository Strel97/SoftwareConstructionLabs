package com.strel.lab;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Created by Сергей on 06.12.2015.
 */
public class MyFilter implements Filter {
    public boolean isLoggable(LogRecord record) {
        Object[] params = record.getParameters();
        double pfx = (Double) params[0];
        double tol = (Double) params[1];

        return Math.abs(pfx)< tol;
    }
}
