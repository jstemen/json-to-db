package com.jaredstemen.blogspot;

import com.jaredstemen.blogspot.jsonimport.JsonFileImporter;

import com.jaredstemen.blogspot.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.*;
import java.text.ParseException;
import java.util.List;
import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created with IntelliJ IDEA.
 * User: Jared Stemen
 * Date: 1/18/14
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class,loader = AnnotationConfigContextLoader.class)
public class JsonFileImporterTest {

    @Inject
    private JsonFileImporter jsonFileImporter;

    @Inject
    private ProductRepository productRepository;

    @Test
   public void testBindJsonToProduct() throws IOException, ParseException {
        jsonFileImporter.importJsonTextFileToDb();
        List<Product> products =  productRepository.findAll();

        assertThat(products.size(), equalTo(10000));
    }
}
