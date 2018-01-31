package shadskii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Component that allows for initialization operations on a database.
 */
@Component
public class DbInitializer
{
    private final LineRepository repository;

    /**
     * Instantiates a DbInitializer.
     *
     * @param repository - The repository this initializer should operate on.
     */
    @Autowired
    public DbInitializer(LineRepository repository)
    {
        this.repository = repository;
    }

    /**
     * Initializes the database to hold the lines of the argument file. This deletes all the lines that are currently in
     * the database and replaces them with the lines read form the argument file. The file to be loaded must meet the
     * following criteria:
     * <pre><ul>
     *     <li> Each line is terminated with a newline ("\n"). </li>
     *     <li> Any given line will fit into memory. </li>
     *     <li> The line is valid ASCII (e.g. not Unicode). </li>
     * </pre></ul>
     *
     * @param file File to be read.
     */
    public void loadLines(Path file)
    {
        repository.deleteAll();
        try (Stream<String> stream = Files.lines(file))
        {
            List<String> lines = stream.collect(Collectors.toList());
            for (int i = 0; i < lines.size(); i++)
            {
                repository.save(new Line((long) i, lines.get(i)));
            }
        }
        catch (IOException e)
        {
            //TODO: Replace with logger
            e.printStackTrace();
        }
    }
}
