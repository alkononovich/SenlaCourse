package com.senla.training.kononovich.server.model;

import javax.persistence.*;

@Entity
@Table(name = "user_autifification")
public class User extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158384486973004533L;
	
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
