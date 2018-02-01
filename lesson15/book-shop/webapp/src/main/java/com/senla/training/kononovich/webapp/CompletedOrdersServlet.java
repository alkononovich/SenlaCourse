package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.kononovich.api.core.IOrderService;
import com.senla.training.kononovich.dao.daoimpl.FactoryUtil;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Order;

public class CompletedOrdersServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8458101317612557420L;
	private static final Logger logger = Logger.getLogger(CompletedOrdersServlet.class);
	private IOrderService shop = (IOrderService) DependencyInjection.getClassInstance(IOrderService.class);
	private EntityManager em;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String strStart = request.getParameter("start");
		String strEnd = request.getParameter("end");
		Date start = new Date();
		Date end = new Date();
		try {
			start = sdf.parse(strStart);
			end = sdf.parse(strEnd);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		 
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = "";
		List<Order> orders = shop.completedOrdersByTime(em, start, end);
		json = mapper.writeValueAsString(orders);
		
		out.println("{\"Result\":" + json + "}");
		em.close();
	}
}
