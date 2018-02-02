package com.github.shadskii.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.nio.file.Paths;

@EnableMongoRepositories({"com.github.shadskii"})
@SpringBootApplication
public class LoaderApplication implements CommandLineRunner
{
    @Autowired
    private DbInitializer initializer;
    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args)
    {
        SpringApplication.run(LoaderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (args.length < 1)
        {
            throw new IllegalArgumentException("Please specify a file to load");
        } else if (args.length > 1)
        {
            throw new IllegalArgumentException("Please only specify one file");
        }
        initializer.loadLines(Paths.get(args[0]));
    }
}
