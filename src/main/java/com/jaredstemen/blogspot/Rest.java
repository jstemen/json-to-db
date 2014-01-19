package com.jaredstemen.blogspot;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Expenses")
public class Rest {

    private static Logger LOGGER = Logger.getLogger(Rest.class.toString());

	/*@GET
    @Path("/{monthYearStr}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expense> getMsg(@PathParam("monthYearStr") String monthYearStr) {
        LOGGER.info("In the get part");
		List<Expense> expenses= ExpenseTable.getExpenseTable().getExpensesForMonth(monthYearStr);
        //expenses.add(new Expense(new Date(), "I amd the description", "I'm the catagroiy", "I'm the aub catabroy", 3.2, "walmart"));
        return expenses;
	}

	
	@POST
	@Path("/{monthYearStr}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void getMsg(@PathParam("monthYearStr") String monthYearStr, List<Expense> expenses) {
        LOGGER.info("In the get part");
		ExpenseTable expenseTable= ExpenseTable.getExpenseTable();
		expenseTable.setExpesnseForMonth(monthYearStr, expenses);
	}*/
}