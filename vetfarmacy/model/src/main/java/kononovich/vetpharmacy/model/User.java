package kononovich.vetpharmacy.model;

import java.util.List;

import javax.persistence.*;

import kononovich.vetpharmacy.model.enums.UserRole;

@Entity
@Table
public class User extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2305173765131135163L;

	@Column
	private String name;

	@Column
	private String address;

	@Column
	private UserRole role;

	@OneToOne(optional = false, mappedBy = "user")
	private UserCreds creds;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Animal> animals;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Order> orders;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private Queue vaccineQueue;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Queue getVaccineQueue() {
		return vaccineQueue;
	}

	public void setVaccineQueue(Queue vaccineQueue) {
		this.vaccineQueue = vaccineQueue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserCreds getCreds() {
		return creds;
	}

	public void setCreds(UserCreds creds) {
		this.creds = creds;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

}
