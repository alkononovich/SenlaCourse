package com.senla.training.kononovich.userinterface;

import com.senla.training.kononovich.userinterface.menu.MenuBuilder;
import com.senla.training.kononovich.userinterface.menu.items.*;
import com.senla.training.kononovich.userinterface.executors.book.*;
import com.senla.training.kononovich.userinterface.executors.claim.*;
import com.senla.training.kononovich.userinterface.executors.order.*;

public class MainMenu {
	public static void Start() {
		Menu root = new Menu("Main");
		
		Menu booksMenu = new Menu("Books", root);
		Menu ordersMenu = new Menu("Orders", root);
		Menu claimsMenu = new Menu("Claims", root);
		
		Menu viewBooks = new Menu("View books", booksMenu);
		Menu viewOrders = new Menu("View orders", ordersMenu);
		
		root.getMenu().add(booksMenu);
		root.getMenu().add(ordersMenu);
		root.getMenu().add(claimsMenu);
		
		booksMenu.getMenu().add(new Button("Add book", new AddBook(), booksMenu));
		booksMenu.getMenu().add(new Button("Remove book", new RemoveBook(), booksMenu));
		booksMenu.getMenu().add(new Button("Update book", new UpdateBook(), booksMenu));
		booksMenu.getMenu().add(new Button("Read books from file", new ReadBooksFromFile(), booksMenu));
		booksMenu.getMenu().add(new Button("Write books to file", new WriteBooksToFile(), booksMenu));	
		booksMenu.getMenu().add(viewBooks);
		
		viewBooks.getMenu().add(new Button("View all books",  new ViewBooks(), viewBooks));
		viewBooks.getMenu().add(new Button("View old books",  new ViewBooks(), viewBooks));
		
		ordersMenu.getMenu().add(new Button("Add order", new AddOrder(), ordersMenu));
		ordersMenu.getMenu().add(new Button("Remove", new RemoveOrder(), ordersMenu));
		ordersMenu.getMenu().add(new Button("Update order", new UpdateOrder(), ordersMenu));
		ordersMenu.getMenu().add(new Button("Complete order", new CompleteOrder(), ordersMenu));
		ordersMenu.getMenu().add(viewOrders);
		
		viewOrders.getMenu().add(new Button("View all orders", new ViewOrders(), viewOrders));
		viewOrders.getMenu().add(new Button("View completed orders for a period", new ViewCompletedOrders(), viewOrders));
		viewOrders.getMenu().add(new Button("Total number of completed orders", new ViewCountOfCompleted(), viewOrders));
		viewOrders.getMenu().add(new Button("View orders for book", new ViewOrdersOnBook(), viewOrders));
		viewOrders.getMenu().add(new Button("View total sum of money for a period", new ViewSumByTime(), viewOrders));
		
		claimsMenu.getMenu().add(new Button("Add claim", new AddClaim(), claimsMenu));
		claimsMenu.getMenu().add(new Button("Remove claim", new RemoveClaim(), claimsMenu));
		claimsMenu.getMenu().add(new Button("Update claim", new UpdateClaim(), claimsMenu));
		claimsMenu.getMenu().add(new Button("View all claims", new ViewClaims(), claimsMenu));
		
		MenuBuilder.buildMenu(root);
	}
}
