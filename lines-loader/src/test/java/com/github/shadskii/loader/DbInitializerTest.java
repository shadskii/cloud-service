package com.github.shadskii.loader;

import com.github.shadskii.data.Line;
import com.github.shadskii.data.LineRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DbInitializerTest
{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testLoadLines() throws IOException
    {
        Path inputFile = folder.newFile("input")
                               .toPath();
        Files.write(inputFile, Arrays.asList("Hello", "World"), Charset.forName("UTF-8"));

        List<Line> lineList = new ArrayList<>();
        LineRepository repository = mock(LineRepository.class);
        when(repository.save(any(Line.class))).then(a ->
        {
            Line l = a.getArgument(0);
            lineList.add(l);
            return l;
        });


        DbInitializer initializer = new DbInitializer(repository);
        initializer.loadLines(inputFile);

        verify(repository, times(1)).deleteAll();
        assertThat(lineList).hasSize(2);

        Line line0 = lineList.get(0);
        assertThat(line0.getPageNum()).isEqualTo(0);
        assertThat(line0.getLine()).isEqualTo("Hello");

        Line line1 = lineList.get(1);
        assertThat(line1.getPageNum()).isEqualTo(1);
        assertThat(line1.getLine()).isEqualTo("World");
    }
}
