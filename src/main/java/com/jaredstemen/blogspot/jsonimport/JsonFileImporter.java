package com.jaredstemen.blogspot.jsonimport;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.jaredstemen.blogspot.CategoryData;
import com.jaredstemen.blogspot.Product;
import com.jaredstemen.blogspot.repository.CategoryDataRepository;
import com.jaredstemen.blogspot.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Handles the importing of JSON data from a text file into a db.
 * User: Jared Stemen Stemen
 * Date: 1/18/14
 * Time: 2:14 PM
 */
@Component
@Transactional()
public class JsonFileImporter {
    private static final Logger LOG = LoggerFactory.getLogger(JsonFileImporter.class);
    final static Charset ENCODING = StandardCharsets.UTF_8;
    @Value("${json.file.location}")
    private String jsonPath;
    private JsonParser jsonParser;

    @Inject
    private CategoryDataRepository categoryDataRepository;

    @Inject
    private ProductRepository productRepository;

    /*
    Sample Format of JSON to be imported :
            "createdDate": "2013-01-23 01:59:05",
            "imageUrl": "http://images.example.com/product/converted/000000/000000000000.jpg",
            "title": "WIRE 18 AWG WHITE 35 FEET GB",
            "category": "ACCESSORIES/MISC",
            "isActive": "0",
            "popularityIndex": "NULL",
            "itemId": "115298",
            "parentCategory": "ELECTRICAL ACCESSORIES",
            "department": "ROUGH ELECTRICAL",
            "upc": "032076053123",
            "brand": "Gardner Bender",
            "modifiedDate": "2013-02-08 10:09:30",
            "itemHashint64": "20211613172483"*/

    /**
     * Imports JSON data from file into database
     * @throws IOException
     * @throws ParseException
     */
    public void importJsonTextFileToDb() throws IOException, ParseException {
        File src = new File(jsonPath);
        JsonFactory jsonFactory = new JsonFactory();
        jsonParser = jsonFactory.createJsonParser(src);

        //Fast forward past some junk at beginning of file
        jsonParser.nextToken();
        jsonParser.nextToken();
        jsonParser.nextToken();
        jsonParser.nextToken();  //end of junk

        //Parse and persist products one at a time to save memory
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

            Product inProduct = new Product();
            inProduct.setCreatedDate(extractDate("createdDate"));

            inProduct.setImageUrl(extractString("imageUrl"));
            inProduct.setTitle(extractString("title"));
            String tempCat = extractString("category");
            inProduct.setActive(extractString("isActive"));
            inProduct.setPopularityIndex(extractString("popularityIndex"));
            inProduct.setItemId(extractString("itemId"));
            String tempParentCat = extractString("parentCategory");
            inProduct.setUpc(extractString("department"));
            inProduct.setUpc(extractString("upc"));
            inProduct.setBrand(extractString("brand"));
            inProduct.setModifiedDate(extractDate("modifiedDate"));
            inProduct.setItemHashint64(extractString("itemHashint64"));
            jsonParser.nextToken();//skip end of object

            linkCategoryInfoToProduct(inProduct, tempCat, tempParentCat);
            LOG.info("Product has been created.");
        }
        jsonParser.close();
    }

    private void linkCategoryInfoToProduct(Product inProduct, String category, String parentCategory) {
        CategoryData categoryData = categoryDataRepository.findByParentCategoryAndCategory(parentCategory, category);
        if (categoryData == null) { //make it
            categoryData = new CategoryData();
            categoryData.setCategory(category);
            categoryData.setParentCategory(parentCategory);
            categoryData = categoryDataRepository.save(categoryData);
        }
        inProduct.setCategoryData(categoryData);
        productRepository.save(inProduct);
    }

    /**
     * Extract a date from jsonParser
     * @param key   The name of the date expected
     * @return  The Date
     * @throws IOException
     * @throws ParseException
     */
    private Date extractDate(String key) throws IOException, ParseException {
        String currentName = jsonParser.getCurrentName();
        Date date = null;
        if (key.equals(currentName)) { // contains an object
            jsonParser.nextToken(); // move to value
            String text = jsonParser.getText();
            //Expected date format: 2013-01-23 01:59:05
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

            date = simpleDateFormat.parse(text);

            jsonParser.nextToken();
        }
        return date;
    }

    /**
     * Extracts String from jsonParser
     * @param key   The name of the key
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    private String extractString(String key) throws IOException, IllegalStateException {
        String value = null;
        String currentName = jsonParser.getCurrentName();
        if (key.equals(currentName)) {
            jsonParser.nextToken(); // move to value
            value = jsonParser.getText();
        } else {
            throw new IllegalStateException(String.format("Key: %s did not match expected value: %s", currentName, key));
        }
        jsonParser.nextToken();
        return value;
    }

}
