package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.kononovich.api.core.IBookOrderService;
import com.senla.training.kononovich.dao.daoimpl.FactoryUtil;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Order;

public class OrdersOfBookServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3358104180852370874L;
	
	private IBookOrderService shop = (IBookOrderService) DependencyInjection.getClassInstance(IBookOrderService.class);
	private EntityManager em;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String id = request.getParameter("id");
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = "";
		List<Order> orders = shop.ordersOfBook(em, new Integer(id));
		json = mapper.writeValueAsString(orders);
		
		out.println("{\"Result\":" + json + "}");
		em.close();
	}

}
