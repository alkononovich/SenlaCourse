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
import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.dao.daoimpl.FactoryUtil;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Book;

public class OldBooksServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3672396851492958513L;
	private IBookService shop = (IBookService) DependencyInjection.getClassInstance(IBookService.class);
	private EntityManager em;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String month = request.getParameter("month");
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = "";
		List<Book> books = shop.oldBooks(em, Integer.parseInt(month));

		json = mapper.writeValueAsString(books);

		out.println("{\"Result\":" + json + "}");
		em.close();
	}
}
