package com.github.shadskii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;

@SpringBootApplication
public class Server implements CommandLineRunner
{

    @Autowired
    private DbInitializer initializer;

    public static void main(String[] args)
    {
        SpringApplication.run(Server.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        initializer.loadLines(Paths.get("shakespeare.txt"));
    }
}
