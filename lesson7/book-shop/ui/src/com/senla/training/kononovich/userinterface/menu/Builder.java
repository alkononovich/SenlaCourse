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
		
		root.getMenu().add(new MenuItem("Books", booksMenu));
		root.getMenu().add(new MenuItem("Orders", ordersMenu));
		root.getMenu().add(new MenuItem("Claims", claimsMenu));
		
		booksMenu.getMenu().add(new MenuItem("Add book", new AddBook()));
		booksMenu.getMenu().add(new MenuItem("Remove book", new RemoveBook()));
		booksMenu.getMenu().add(new MenuItem("Update book", new UpdateBook()));
		booksMenu.getMenu().add(new MenuItem("Read books from file", new ReadBooksFromFile()));
		booksMenu.getMenu().add(new MenuItem("Write books to file", new WriteBooksToFile()));	
		booksMenu.getMenu().add(new MenuItem("View Books", viewBooks));
		
		viewBooks.getMenu().add(new MenuItem("View all books",  new ViewBooks()));
		viewBooks.getMenu().add(new MenuItem("View old books",  new ViewOldBooks()));
		
		ordersMenu.getMenu().add(new MenuItem("Add order", new AddOrder()));
		ordersMenu.getMenu().add(new MenuItem("Remove order", new RemoveOrder()));
		ordersMenu.getMenu().add(new MenuItem("Update order", new UpdateOrder()));
		ordersMenu.getMenu().add(new MenuItem("Complete order", new CompleteOrder()));
		ordersMenu.getMenu().add(new MenuItem("Clone order", new CloneOrder()));
		ordersMenu.getMenu().add(new MenuItem("Write orders to file", new WriteOrdersToFile()));
		ordersMenu.getMenu().add(new MenuItem("Read orders from file", new ReadOrdersFromFile()));
		ordersMenu.getMenu().add(new MenuItem("View Orders", viewOrders));
		
		viewOrders.getMenu().add(new MenuItem("View all orders", new ViewOrders()));
		viewOrders.getMenu().add(new MenuItem("View completed orders for a period", new ViewCompletedOrders()));
		viewOrders.getMenu().add(new MenuItem("Total number of completed orders", new ViewCountOfCompleted()));
		viewOrders.getMenu().add(new MenuItem("View orders for book", new ViewOrdersOnBook()));
		viewOrders.getMenu().add(new MenuItem("View total sum of money for a period", new ViewSumByTime()));
		
		claimsMenu.getMenu().add(new MenuItem("Add claim", new AddClaim()));
		claimsMenu.getMenu().add(new MenuItem("Remove claim", new RemoveClaim()));
		claimsMenu.getMenu().add(new MenuItem("Update claim", new UpdateClaim()));
		claimsMenu.getMenu().add(new MenuItem("Write claims to file", new WriteClaimsToFile()));
		claimsMenu.getMenu().add(new MenuItem("Read claims from file", new ReadClaimsFromFile()));
		claimsMenu.getMenu().add(new MenuItem("View all claims", new ViewClaims()));
		
		return root;
		}
}
