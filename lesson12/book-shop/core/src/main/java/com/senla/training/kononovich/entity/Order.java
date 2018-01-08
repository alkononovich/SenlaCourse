package com.senla.training.kononovich.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.senla.training.kononovich.enums.Status;

@Entity
@Table(name="order")
public class Order extends AbstractModel implements Cloneable, Identified<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4376601678405872708L;
	
	@OneToMany(targetEntity=Book.class, mappedBy="id", fetch = FetchType.LAZY)
	private List<Book> books;
	
	@Column(name="client")
	private String client;
	
	@Column(name="executionDate")
	private Date executionDate;
	
	@Column(name="order_status")
	private Status status;
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order() {
		super();
		this.books = new ArrayList<Book>();
	}
	
	public Order(String client, Book... book) {
		super();
		this.books = new ArrayList<Book>();
		books.addAll(Arrays.asList(book));
		this.client = client;
		this.status = Status.ORDRERED;
	}

	public Order(String client, List<Book> book) {
		super();
		this.books = new ArrayList<Book>();
		books.addAll(book);
		this.client = client;
		this.status = Status.ORDRERED;
	}

	public Order(int id, String client, List<Book> book) {
		this(client, book);
		this.setId(id);
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getCost() {
		int s = 0;
		if (!books.isEmpty()) {
			for (Book book : books) {
				s += book.getCost();
			}
		}
		return s;
	}

	@Override
	public Order clone() {
		Order clone = new Order(this.client, this.getBooks());
		clone.setStatus(this.getStatus());
		if (this.getExecutionDate() != null) {
			clone.setExecutionDate(this.getExecutionDate());
		}
		return clone;
	}

	@Override
	public String view() {
		StringBuffer str = new StringBuffer();
		if (status == Status.COMPLETED) {
			str.append(getId()).append(";").append(client).append(";").append(books).append(";").append(status)
					.append(";").append(executionDate.toString());
		} else {
			str.append(getId()).append(";").append(client).append(";").append(books).append(";").append(status);
		}
		return str.toString();
	}

}
