package com.jaredstemen.blogspot.jsonimport;

import com.jaredstemen.blogspot.AppConfig;
import com.jaredstemen.blogspot.jsonimport.ImportProduct;
import com.jaredstemen.blogspot.jsonimport.ImportProductHolder;
import com.jaredstemen.blogspot.jsonimport.JsonFileImporter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;

public class Runner {
    public static void main() throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        JsonFileImporter jsonFileImporter = ctx.getBean(JsonFileImporter.class);

        jsonFileImporter.convertJsonToObjAndPersist(jsonFileImporter);

    }


}