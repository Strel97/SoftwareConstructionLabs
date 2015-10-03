package com.strel.lab.logic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


/**
 * Created by DemonSlayer on 01.10.2015.
 */
public class FileProcessor {
    public static String readText(File file)
        throws IOException
    {
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static void saveToFile(String filename, String dir, String content) {
        try {
            PrintWriter out = new PrintWriter(new File(dir + "/" + filename));
            out.write(content);
            out.close();
        }
        catch (IOException ex) {
            System.err.println("I don't know what the hell is this, but here is: \n" + ex.getMessage());
        }
    }
}
