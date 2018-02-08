package com.senla.training.kononovich.server.model;

import javax.persistence.*;


@Entity
@Table(name = "user_data")
public class Profile extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1537354694934785128L;
	
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    
    public Profile() {
    	
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}



}
