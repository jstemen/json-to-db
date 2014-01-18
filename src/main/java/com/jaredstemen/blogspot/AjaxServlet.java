/*package com.stemen;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*//**
 * Servlet implementation class AjaxServlet
 *//*
@WebServlet("/Expenses")
public class AjaxServlet extends HttpServlet {
	public static final String MONTH_YEAR_STR = "monthYearStr";

	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxServlet.class);
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public AjaxServlet() {
        super();
        LOGGER.info("Servlet has been created");
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("servlet has received get request.");
        String monthYearStr = request.getParameter(MONTH_YEAR_STR);
        List<Expense> expenses= ExpenseTable.getExpenseTable().getExpensesForMonth(monthYearStr);
        expenses.add(new Expense(new Date(), "I amd the description", "I'm the catagroiy", "I'm the aub catabroy", 3.2, "walmart"));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expensesJson = ow.writeValueAsString(expenses);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(expensesJson);       // Write response body.
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();  
	    Animal animal = mapper.readValue(request.getReader(), ExpenseTable.class); 
		.
	}

}
*/