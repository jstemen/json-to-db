package com.jaredstemen.blogspot.jsonimport;

import com.fasterxml.jackson.core.JsonFactory;
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
import com.fasterxml.jackson.core.JsonParser;
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
 * Created with IntelliJ IDEA.                                      t
 * User: jared
 * Date: 1/18/14
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Transactional()
public class JsonFileImporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileImporter.class);
    final static Charset ENCODING = StandardCharsets.UTF_8;
    @Value("${json.file.location}")
    private String jsonPath;
    private JsonParser jp;

    @Inject
    private CategoryDataRepository categoryDataRepository;

    @Inject
    private ProductRepository productRepository;

    /*"createdDate": "2013-01-23 01:59:05",
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
    public void importJsonTextFileToDb() throws IOException, ParseException {
        File src = new File(jsonPath);
        JsonFactory f = new JsonFactory();
        jp = f.createJsonParser(src);
        jp.nextToken();
        jp.nextToken();
        jp.nextToken();
        jp.nextToken();  //end of junk
        while (jp.nextToken() != JsonToken.END_OBJECT) {

            //jp.nextToken(); // move to value
            Product inProduct = new Product();
            inProduct.setCreatedDate(extractDate("createdDate"));

            inProduct.setImageUrl(extractValue("imageUrl"));
            inProduct.setTitle(extractValue("title"));
            String tempCat =  extractValue("category");
            inProduct.setActive(extractValue("isActive"));
            inProduct.setPopularityIndex(extractValue("popularityIndex"));
            inProduct.setItemId(extractValue("itemId"));
            String tempParentCat = extractValue("parentCategory");
            inProduct.setUpc(extractValue("department"));
            inProduct.setUpc(extractValue("upc"));
            inProduct.setBrand(extractValue("brand"));
            inProduct.setModifiedDate(extractDate("modifiedDate"));
            inProduct.setItemHashint64(extractValue("itemHashint64"));
            jp.nextToken();
            CategoryData categoryData= categoryDataRepository.findByParentCategoryAndCategory(tempParentCat, tempCat);
            if(categoryData == null){
                categoryData = new CategoryData();
                categoryData.setCategory(tempCat);
                categoryData.setParentCategory(tempParentCat);
                categoryData = categoryDataRepository.save(categoryData);
            }
            inProduct.setCategoryData(categoryData);
            productRepository.save(inProduct);

        }
        jp.close(); // ensure resources get cleaned up timely and properly
    }

    private Date extractDate(String key) throws IOException, ParseException {

        String currentName = jp.getCurrentName();
        Date date = null;
        if (key.equals(currentName)) { // contains an object
            jp.nextToken(); // move to value
            String text = jp.getText();
            //2013-01-23 01:59:05
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

                date = simpleDateFormat.parse(text);

            jp.nextToken();
        }
        return date;
    }

    private String extractValue(String key) throws IOException, IllegalStateException {
        String value = null;
        String currentName = jp.getCurrentName();
        if (key.equals(currentName)) {
            jp.nextToken(); // move to value
            value = jp.getText();
        }else{
            throw new IllegalStateException(String.format("Key: %s did not match expected value: %s", currentName, key));
        }
        jp.nextToken();
        return value;
    }

}
