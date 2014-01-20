package com.jaredstemen.blogspot.jsonimport;

import com.jaredstemen.blogspot.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.text.ParseException;

public class Runner {
    public static void main() throws IOException, ParseException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        JsonFileImporter jsonFileImporter = ctx.getBean(JsonFileImporter.class);

        jsonFileImporter.importJsonTextFileToDb();

    }


}