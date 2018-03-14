package kononovich.vetpharmacy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class News extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6499555459250070198L;

	@Column
	private Date date;
	
	@Column
	private String text;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
