package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.kononovich.controller.IShop;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Order;

public class OrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2980688985920329437L;
	private IShop shop = (IShop) DependencyInjection.getClassInstance(IShop.class);


	/*public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		String action = request.getParameter("action");
		IShop shop = Shop.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();

		Order order = new Order();
		
		
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.readValue(json, Order.class);

		shop.addOrder(order);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = "";
		Order order = shop.getOrderById(new Integer(id));
		json = mapper.writeValueAsString(order);
		
		out.println("{\"Result\":" + json + "}");
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Order order = new Order();
		order = mapper.readValue(json, Order.class);

		Integer orderId = order.getId();

		shop.removeOrder(orderId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
	}
}
