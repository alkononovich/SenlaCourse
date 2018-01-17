package com.senla.training.kononovich.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book extends AbstractModel implements Identified<Integer>  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 335376399320755204L;
	@Column(name="book_name")
	private String name;
	
	@Column(name="book_cost")
	private int cost;
	
	@Column(name="book_count")
	private int count;
	
	@Column(name="publicationDate")
	private Date publicationDate;
	
	@Column(name="receiptDate")
	private Date receiptDate;
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;

	public Book() {
		super();
	}	
	
	public Book(String name, int cost, Date publicationDate) {
		super();
		this.name = name;
		this.cost = cost;
		this.publicationDate = publicationDate;
		this.receiptDate = new Date();
		this.count = 1;
	}
	
	public Book(String name, int cost, Date publicationDate, int count) {
		this(name, cost, publicationDate);
		this.count = count;
	}
	
	public Book(int id, String name, int cost, Date publicationDate, int count) {
		this(name, cost, publicationDate, count);
		this.setId(id);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String view() {
		StringBuffer str = new StringBuffer();
		str.append(getId()).append(";").append(name).append(";").append(cost).append(";").append(publicationDate.toString()).append(";").append(receiptDate.toString()).append(";").append(count);
		return str.toString();
	}	
	
	@Override
	public String toString() {
		return name;
	}
}
