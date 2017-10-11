package com.senla.training.kononovich.userinterface.menu;

import com.senla.training.kononovich.userinterface.menu.items.*;
import com.senla.training.kononovich.userinterface.executors.book.*;
import com.senla.training.kononovich.userinterface.executors.claim.*;
import com.senla.training.kononovich.userinterface.executors.order.*;

public class Builder {
	private Menu root;
	public Menu buildMenu() {
		root = new Menu("Main");
		
		Menu booksMenu = new Menu("Books", root);
		Menu ordersMenu = new Menu("Orders", root);
		Menu claimsMenu = new Menu("Claims", root);
		
		Menu viewBooks = new Menu("View books", booksMenu);
		Menu viewOrders = new Menu("View orders", ordersMenu);
		
		root.getMenu().add(booksMenu);
		root.getMenu().add(ordersMenu);
		root.getMenu().add(claimsMenu);
		
		booksMenu.getMenu().add(new Button("Add book", new AddBook()));
		booksMenu.getMenu().add(new Button("Remove book", new RemoveBook()));
		booksMenu.getMenu().add(new Button("Update book", new UpdateBook()));
		booksMenu.getMenu().add(new Button("Read books from file", new ReadBooksFromFile()));
		booksMenu.getMenu().add(new Button("Write books to file", new WriteBooksToFile()));	
		booksMenu.getMenu().add(viewBooks);
		
		viewBooks.getMenu().add(new Button("View all books",  new ViewBooks()));
		viewBooks.getMenu().add(new Button("View old books",  new ViewBooks()));
		
		ordersMenu.getMenu().add(new Button("Add order", new AddOrder()));
		ordersMenu.getMenu().add(new Button("Remove", new RemoveOrder()));
		ordersMenu.getMenu().add(new Button("Update order", new UpdateOrder()));
		ordersMenu.getMenu().add(new Button("Complete order", new CompleteOrder()));
		ordersMenu.getMenu().add(viewOrders);
		
		viewOrders.getMenu().add(new Button("View all orders", new ViewOrders()));
		viewOrders.getMenu().add(new Button("View completed orders for a period", new ViewCompletedOrders()));
		viewOrders.getMenu().add(new Button("Total number of completed orders", new ViewCountOfCompleted()));
		viewOrders.getMenu().add(new Button("View orders for book", new ViewOrdersOnBook()));
		viewOrders.getMenu().add(new Button("View total sum of money for a period", new ViewSumByTime()));
		
		claimsMenu.getMenu().add(new Button("Add claim", new AddClaim()));
		claimsMenu.getMenu().add(new Button("Remove claim", new RemoveClaim()));
		claimsMenu.getMenu().add(new Button("Update claim", new UpdateClaim()));
		claimsMenu.getMenu().add(new Button("View all claims", new ViewClaims()));
		
		return root;
		}
}
