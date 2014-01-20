package rest;

import com.jaredstemen.blogspot.CategoryData;
import com.jaredstemen.blogspot.Product;
import com.jaredstemen.blogspot.repository.CategoryDataRepository;
import com.jaredstemen.blogspot.repository.ProductRepository;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Rest {

    private static Logger LOGGER = Logger.getLogger(Rest.class.toString());

    @Inject
    CategoryDataRepository categoryDataRepository;
    @Inject
    ProductRepository productRepository;


    /*/search/category - list all categories
    /search/category/{category} - list all product records in the given category
    /search/category/{category}/keyword/{word} - returns all records in the given category which also match the keyword*/

	@GET
    @Path("/search/category")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryData> getAllCategories(@PathParam("monthYearStr") String monthYearStr) {
        LOGGER.info("In the get part");
		List<CategoryData> categoryDataList= categoryDataRepository.findAll();
        return categoryDataList;
	}

    @GET
    @Path("/search/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductsInCategory(@PathParam("category") String category) {
        LOGGER.info("In the get part");
        List<Product> products= productRepository.findByCategoryData_category(category);
        return products;
    }
/*
	
	@GET
	@Path("/search/category/{category}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void getMsg(@PathParam("monthYearStr") String monthYearStr, List<Expense> expenses) {
        LOGGER.info("In the get part");
		ExpenseTable expenseTable= ExpenseTable.getExpenseTable();
		expenseTable.setExpesnseForMonth(monthYearStr, expenses);
	}*/
}