package com.example.projectnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //To discard auto-config and add own configuration
public class ProjectNoteApplication {

    public static void main(String[] args){
        SpringApplication.run(ProjectNoteApplication.class, args);
    }

}
