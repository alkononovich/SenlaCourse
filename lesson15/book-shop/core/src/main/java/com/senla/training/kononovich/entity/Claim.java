package com.senla.training.kononovich.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.senla.training.kononovich.enums.Status;

@Entity
@Table(name="claim")
public class Claim extends AbstractModel implements Identified<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 455320977275344269L;
	
	@Column(name="book_name")
	private String book;
	
	@Column(name="claim_status")
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
	
	public Claim() {
		super();
	}
	
	public Claim(String book) {
		super();
		this.book = book;
		this.status = Status.ORDRERED;
	}
	
	public Claim(int id, String book) {
		this(book);
		this.setId(id);
	}

	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String view () {
		StringBuffer str = new StringBuffer();
		str.append(getId()).append(";").append(book).append(";").append(status);
		
		return str.toString();
	}
}
