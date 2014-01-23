package com.jaredstemen.blogspot.jsonimport;

import com.jaredstemen.blogspot.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.text.ParseException;

/**
 * Run this class to extract data from the JSON file and put it in the db.
 */
public class JsonFileImporterRunner {
    public static void main(String[] args) throws IOException, ParseException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        JsonFileImporter jsonFileImporter = ctx.getBean(JsonFileImporter.class);

        jsonFileImporter.importJsonTextFileToDb();

    }


}