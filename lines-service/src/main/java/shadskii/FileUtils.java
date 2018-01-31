package shadskii;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils
{
    public static void main(String[] args) throws IOException
    {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
        {
            lines.add(String.format("This line is line %d", i));
        }

        Path file = Paths.get("testFile_1000.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
    }
}
