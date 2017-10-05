package com.senla.training.kononovich.service;

public class ServiceManager {
	public static BookService bookService = new BookService();
	public static OrderService orderService = new OrderService();
	public static ClaimService claimService = new ClaimService();
	public static BookOrderService bookOrderService = new BookOrderService();
	public static BookClaimService bookClaimService = new BookClaimService();
}
