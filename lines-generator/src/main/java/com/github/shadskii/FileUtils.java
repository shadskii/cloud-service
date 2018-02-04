package com.github.shadskii;

import java.io.*;

public class FileUtils
{
    public static void main(String[] args) throws IOException
    {
        generateTestFile("testFile_10_000.txt", 10_000);
    }

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
