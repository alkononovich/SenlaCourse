package kononovich.vetpharmacy.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table
public class Vaccine extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6413651937192785817L;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "VACCINE_ID")
    private Animal owner;
	
	@Column
	private String name;
	
	@Column
	private Date date;

	public Animal getOwner() {
		return owner;
	}

	public void setOwner(Animal owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
