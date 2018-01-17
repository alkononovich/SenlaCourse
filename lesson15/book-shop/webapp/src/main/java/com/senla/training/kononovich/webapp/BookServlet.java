package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.kononovich.controller.*;
import com.senla.training.kononovich.entity.Book;

public class BookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6452238432153747073L;

	/*public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		String action = request.getParameter("action");
		IShop shop = Shop.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();

		Book book = new Book();
		
		
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		IShop shop = Shop.getInstance();
		ObjectMapper mapper = new ObjectMapper();
		Book book = mapper.readValue(json, Book.class);

		shop.addBook(book);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		IShop shop = Shop.getInstance();
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = "";
		Book book = shop.getBookById(Integer.parseInt(id));
		json = mapper.writeValueAsString(book);
		
		out.println("{\"Result\":" + json + "}");
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = request.getParameter("json");
		IShop shop = Shop.getInstance();
		ObjectMapper mapper = new ObjectMapper();
		Book book = new Book();
		book = mapper.readValue(json, Book.class);

		Integer bookId = book.getId();

		shop.removeBook(bookId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
	}

}
