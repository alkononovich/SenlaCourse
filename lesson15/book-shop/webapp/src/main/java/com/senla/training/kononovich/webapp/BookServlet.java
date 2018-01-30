package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;

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

public class BookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6452238432153747073L;
	private IBookService shop = (IBookService) DependencyInjection.getClassInstance(IBookService.class);
	private EntityManager em;


	/*public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		String action = request.getParameter("action");
		IShop shop = Shop.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();

		Book book = new Book();
		
		
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Book book = mapper.readValue(json, Book.class);

		shop.addBook(em, book);
		
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
		Book book = shop.getBookById(em, new Integer(id));
		json = mapper.writeValueAsString(book);
		
		out.println("{\"Result\":" + json + "}");
		em.close();
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Book book = new Book();
		book = mapper.readValue(json, Book.class);

		Integer bookId = book.getId();

		shop.removeBook(em, bookId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
		em.close();
	}

}
