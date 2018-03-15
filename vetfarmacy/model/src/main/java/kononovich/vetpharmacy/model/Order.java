package kononovich.vetpharmacy.model;

import java.util.List;

import javax.persistence.*;

import kononovich.vetpharmacy.model.enums.OrderStatus;

@Entity
@Table
public class Order extends AbstractEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 9211513714609174332L;
    
    @Column
    private OrderStatus status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private User user;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private Discount discount;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double cost() {
	double result = 0;
	for (Product p : products) {
	    result += p.getPrice();
	}
	return result * discount.getSize();
    }
}
