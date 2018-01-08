package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.util.List;

import com.senla.training.kononovich.api.*;
import com.senla.training.kononovich.client.RequestSender;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.request.Request;
import com.senla.training.kononovich.request.Response;
import com.senla.training.kononovich.uicontroller.ReaderToField;

public class ClaimController {
	private IPrinter printer = (IPrinter)DependencyInjection.getClassInstance(IPrinter.class);
	
	private ReaderToField reader = ReaderToField.getInstance();

	private static final String ID = "Id: ";
	private static final String BOOK = "Book name: ";
	private static final String PATH = "Path to file: ";
	private static ClaimController instance;

	private ClaimController() {
	}

	public static ClaimController getInstance() {
		if (instance == null) {
			instance = new ClaimController();
		}
		return instance;
	}

	public Claim initializeClaim() {
		printer.print(BOOK);
		String book = reader.readString();
		return new Claim(book);
	}
	
	public List<Claim> getClaimList() {
		Request request = new Request("getClaimList");
		Response response = RequestSender.sendRequest(request);		
		return (List<Claim>)response.getResult();
	}

	public void addClaim() {
		Request request = new Request("addClaim", this.initializeClaim());
		Response response = RequestSender.sendRequest(request);		
	}

	public void removeClaim() {
		printer.printList(getClaimList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= getClaimList().size()) {
			Request request = new Request("removeClaim", id);
			Response response = RequestSender.sendRequest(request);		
		} else {
			printer.print("Invalid Id");
		}
	}

	public void updateClaim() {
		printer.printList(getClaimList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= getClaimList().size()) {
			Request request = new Request("upDateClaim", id, initializeClaim());
			Response response = RequestSender.sendRequest(request);		
		} else {
			printer.print("Invalid Id");
		}
	}

	public void readClaimsFromFile() {
		printer.print(PATH);
		String path = reader.readString();
		Request request = new Request("readClaimsFromFile", path);
		Response response = RequestSender.sendRequest(request);
	}

	public void writeClaimsToFile() {
		printer.print(PATH);
		String path = reader.readString();
		Request request = new Request("writeClaimsToFile", path);
		Response response = RequestSender.sendRequest(request);
	}

}
