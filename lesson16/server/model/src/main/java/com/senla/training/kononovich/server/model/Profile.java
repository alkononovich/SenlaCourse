package com.senla.training.kononovich.server.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "profile")
public class Profile extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1537354694934785128L;
	
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "authentificationId")
    private User user;
    
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    
    public Profile() {
    	
    }
    
    @JsonCreator
    public Profile(
            @JsonProperty("user") User user,
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("dateOfBirth") Date dateOfBirth) {

        super(id);
        this.user = user;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
    
    @JsonCreator
	public Profile(@JsonProperty("user") User user,
            @JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("dateOfBirth") Date dateOfBirth) {
		super();
		this.user = user;
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
    
    
}
