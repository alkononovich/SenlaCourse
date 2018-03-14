package kononovich.vetpharmacy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Discount extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6450341369862326307L;

	@Column
	private String type;
	
	@Column
	private double size;
	
	@OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    private List<Order> orders;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
