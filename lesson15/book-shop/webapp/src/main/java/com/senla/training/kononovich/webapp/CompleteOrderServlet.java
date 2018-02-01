package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.senla.training.kononovich.api.core.IBookOrderService;
import com.senla.training.kononovich.dao.daoimpl.FactoryUtil;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;

public class CompleteOrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8817013033217638289L;
	private IBookOrderService shop = (IBookOrderService) DependencyInjection.getClassInstance(IBookOrderService.class);
	private EntityManager em;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String id = request.getParameter("id");

		shop.completeOrder(em, Integer.parseInt(id));
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
		em.close();
	}
}
