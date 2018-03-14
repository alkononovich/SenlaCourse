package kononovich.vetpharmacy.model;

import javax.persistence.*;

@Entity
@Table
public class Product extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2359747144836887768L;

	@Column
	private ProductType type;
	
	@Column 
	private double price;
	
	@Column
	private int count;
	
	@Column 
	private String name;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private Order order;

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
