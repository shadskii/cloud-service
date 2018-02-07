package com.github.shadskii;

import java.io.*;

public class FileUtils
{
    public static void main(String[] args) throws IOException
    {
        generateTestFile("testFile_10_000.txt", 100_000_000);
    }

    /**
     * Generates a simple file that can be used for test purposes. Each line in the file is in the form: "This line is
     * line [line number].
     *
     * @param filePath Name of the file to create
     * @param numLines Number of lines to write in the file.
     */
    public static void generateTestFile(String filePath, long numLines)
    {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath))))
        {
            for (int i = 0; i < numLines; i++)
            {
                writer.write(String.format("This line is line %d %n", i));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
