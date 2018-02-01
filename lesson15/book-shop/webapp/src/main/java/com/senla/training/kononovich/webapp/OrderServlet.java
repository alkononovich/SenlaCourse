package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.kononovich.api.core.IOrderService;
import com.senla.training.kononovich.dao.daoimpl.FactoryUtil;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Order;

public class OrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2980688985920329437L;
	private IOrderService shop = (IOrderService) DependencyInjection.getClassInstance(IOrderService.class);
	private EntityManager em;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.readValue(json, Order.class);

		if (order.getId() == null) {
			shop.addOrder(em, order);
		} else {
			shop.upDateOrder(em, order);
		}
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
		em.close();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String id = request.getParameter("id");
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = "";
		Order order = shop.getOrderById(em, new Integer(id));
		json = mapper.writeValueAsString(order);
		
		out.println("{\"Result\":" + json + "}");
		em.close();
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Order order = new Order();
		order = mapper.readValue(json, Order.class);

		Integer orderId = order.getId();

		shop.removeOrder(em, orderId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
		em.close();
	}
}
