package com.senla.training.kononovich.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.kononovich.api.core.IClaimService;
import com.senla.training.kononovich.dao.daoimpl.FactoryUtil;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Claim;

public class ClaimServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1740657184793323761L;
	private IClaimService shop = (IClaimService) DependencyInjection.getClassInstance(IClaimService.class);
	private EntityManager em;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Claim claim = mapper.readValue(json, Claim.class);

		if (claim.getId() == null) {
			shop.addClaim(em, claim);
		} else {
			shop.upDateClaim(em, claim);
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
		Claim claim = shop.getClaimById(em, new Integer(id));
		json = mapper.writeValueAsString(claim);
		
		out.println("{\"Result\":" + json + "}");
		em.close();
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		em = FactoryUtil.getEntityManager();
		String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		Claim claim = new Claim();
		claim = mapper.readValue(json, Claim.class);

		Integer claimId = claim.getId();

		shop.removeClaim(em, claimId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"Result\":\"success\"}");
		em.close();
	}
}
