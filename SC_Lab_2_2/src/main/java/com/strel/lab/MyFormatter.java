package com.strel.lab;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Created by Сергей on 06.12.2015.
 */
public class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        ArrayList<Object> params = new ArrayList<Object>();
        for (Object o : record.getParameters())
            params.add(o);

        return "L[" + record.getLevel() + "], " +
                "CauseMethod[" + record.getSourceMethodName() + "], " +
                "MSG: " + record.getMessage() + ", " +
                "Params: " + params + "\n";
    }
}
