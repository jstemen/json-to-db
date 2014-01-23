package rest;

import com.jaredstemen.blogspot.CategoryData;
import com.jaredstemen.blogspot.Product;
import com.jaredstemen.blogspot.repository.CategoryDataRepository;
import com.jaredstemen.blogspot.repository.ProductRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Contains all REST endpoints for the App
 */
@Component
@Path("/")
public class Rest {

    static final Logger LOG = LoggerFactory.getLogger(Rest.class);

    private final CategoryDataRepository categoryDataRepository;

    private final ProductRepository productRepository;

    //In a more robust app, we shouldn't access Data directly via the repositories.
    //It would be better to go through a service.
    @Inject
    public Rest(CategoryDataRepository categoryDataRepository, ProductRepository productRepository) {
        this.categoryDataRepository = categoryDataRepository;
        this.productRepository = productRepository;
    }

    /**
     * List all categories in db
     */
    @GET
    @Path("/search/category")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryData> getAllCategories() {
        LOG.info("Getting all categories...");
        List<CategoryData> categoryDataList = categoryDataRepository.findAll();
        return categoryDataList;
    }

    /**
     * Find all products beloging to category
     * @param category  Category of products to search for
     */
    @GET
    @Path("/search/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductsInCategory(@PathParam("category") String category) {
        LOG.info("Getting all products in category {}", category);
        List<Product> products = productRepository.findByCategoryData_category(category);
        return products;
    }

    /**
     * Find all products in category with word in their title
     * @param category  Category of products to search for
     * @param word  Word in title of products to search for
     */
    @GET
    @Path("/search/category/{category}/keyword/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductsWithTitleMatchingInCategory(@PathParam("category") String category, @PathParam("word") String word) {
        LOG.info("Getting all products in the category {} and with the keyword {}", category, word);
        List<Product> products = productRepository.findByTitleIsContainingIgnoreCaseAndCategoryData_Category(word, category);
        return products;
    }

    /**
     * Find all products with word in their title
     * @param word  Word to search for.
     * @return
     */
    @GET
    @Path("/search/keyword/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductsWithTitleMatching(@PathParam("word") String word) {
        LOG.info("Getting all products with the keyword {}", word);
        List<Product> products = productRepository.findByTitleIsContainingIgnoreCase(word);
        return products;
    }

}