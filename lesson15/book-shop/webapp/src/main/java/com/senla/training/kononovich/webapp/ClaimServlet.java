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
import com.senla.training.kononovich.entity.Claim;

public class ClaimServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1740657184793323761L;
	private IShop shop = (IShop) DependencyInjection.getClassInstance(IShop.class);


	/*public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		String action = request.getParameter("action");
		IShop shop = Shop.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();

		Claim claim = new Claim();
		
		
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Claim claim = mapper.readValue(json, Claim.class);

		shop.addClaim(claim);
		
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
		Claim claim = shop.getClaimById(new Integer(id));
		json = mapper.writeValueAsString(claim);
		
		out.println("{\"Result\":" + json + "}");
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Claim claim = new Claim();
		claim = mapper.readValue(json, Claim.class);

		Integer claimId = claim.getId();

		shop.removeClaim(claimId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
	}
}
