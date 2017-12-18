package com.interview.remotecar;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by naveen on 16/12/17.
 *
 *  *  NOTE:
 *  - This application is packaged as a jar which has Tomcat 8 embedded in Spring Boot Application.
 *  - Make sure you are using JDK 1.8 and Maven 3.x
 *  - Application uses default dates from Application.properties if no options entered when launching the app from command line
 *  - Application runs on default tomcat port 8080. (Override the port no if port used for different service --server.port=9090)
 *  - input dates between year 1900 and 2010
 *
 *  ----------------------------------------------------------------------------------------
 *  STEPS TO RUN THE APPLICATION AND INVOKE REST SERVICE
 *  ---------------------------------------------------------------------------------------
 *
 *  1. Run mvn command to generate the jar file from root project directory
 *   mvn clean package
 *
 *  2. Launch the remotecar-api as Java application from command line
 *  java -jar target/remotecar-api.jar --input="5,5:FLFLFFRFFF"
 *
 *  3. Invoke the Rest Service URI from browser - http://localhost:8080/remotecar/move
 *
 *  If the request is successful, an HTTP status code (200) indicating success is sent along with new position
 *  If an error occurs, an HTTP status code (400) indicating bad request is sent along with error message.
 *
 *  -------------------------------------------------------------------------------------------
 *  DESIGN AND SOLUTION APPROACH
 *  -------------------------------------------------------------------------------------------
 *  Spring Boot Starter Class
 *  		- Spring Boot framework is used to develop the service RemoteControlApplication.java
 *
 *  Rest Controller
 *  		- Date comparison service is exposed as a rest service RemoteCarController.java.
 *
 *  Service Class
 *  		- RemoteCarService.java Provides business logic to calculate new position based on input command
 *
 *  Exception handler
 *  		- RemoteCarExceptionHandler exception handler class
 *

 */

@SpringBootApplication
public class RemoteControlApplication implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteControlApplication.class);

    /**
     * @param args
     */
    public static void main(String ... args) {
        SpringApplication.run(RemoteControlApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
        LOG.info("NonOptionArgs: {}", args.getNonOptionArgs());
        LOG.info("OptionNames: {}", args.getOptionNames());

        for (String name : args.getOptionNames()) {
            LOG.info("arg-" + name + "=" + args.getOptionValues(name));
        }

        boolean containsOption = args.containsOption("input");
        LOG.info("Test data is input: " + containsOption);
    }
}
