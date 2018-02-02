package com.github.shadskii;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server implements CommandLineRunner
{

    public static void main(String[] args)
    {
        int port = Integer.valueOf(args[0]);
        System.getProperties()
              .put("server.port", port);
        SpringApplication.run(Server.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {

    }
}
