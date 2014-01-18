package com.jaredstemen.blogspot;

import com.jaredstemen.blogspot.jsonimport.ImportProductHolder;
import com.jaredstemen.blogspot.jsonimport.JsonFileImporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/18/14
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class,loader = AnnotationConfigContextLoader.class)
public class JsonFileImporterTest {

    @Value("${json.file.location}")
    private String jsonPath;

    @Test
   public void testBindJsonToProduct() throws IOException {
        JsonFileImporter jsonFileImporter = new JsonFileImporter();
        ImportProductHolder productList = jsonFileImporter.bindJsonToProduct(new File(jsonPath));
        assertThat(productList.getProducts().length, equalTo(10000));
    }
}
