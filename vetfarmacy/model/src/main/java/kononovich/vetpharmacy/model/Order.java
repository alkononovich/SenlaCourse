package kononovich.vetpharmacy.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Order extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9211513714609174332L;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Product> products;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private User user;
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
	private Discount discount;
	
	public double cost() {
		double result = 0;
		for(Product p : products) {
			result += p.getPrice();
		}
		return result*discount.getSize();
	}
}
