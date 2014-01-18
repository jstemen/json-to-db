package com.jaredstemen.blogspot.jsonimport;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.                                      t
 * User: jared
 * Date: 1/18/14
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JsonFileImporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileImporter.class);
    final static Charset ENCODING = StandardCharsets.UTF_8;
    @Value("${json.file.location}")
    private String jsonPath;

    public ImportProductHolder bindJsonToProduct(File src) throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        ImportProductHolder productHolder= mapper.readValue(src, ImportProductHolder.class);
        return  productHolder;
    }

    public void convertJsonToObjAndPersist(JsonFileImporter jsonFileImporter) throws IOException {
        ImportProductHolder productHolder = jsonFileImporter.bindJsonToProduct(new File(jsonPath));

        for (ImportProduct product : productHolder.getProducts()) {

        }
    }

}
