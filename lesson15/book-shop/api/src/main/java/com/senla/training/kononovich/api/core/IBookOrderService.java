package com.senla.training.kononovich.api.core;

import java.util.List;

import javax.persistence.EntityManager;

import com.senla.training.kononovich.entity.Order;

public interface IBookOrderService {
	public List<Order> ordersOfBook(EntityManager em, int id);
	public void completeOrder(EntityManager em, int id);
	
}
