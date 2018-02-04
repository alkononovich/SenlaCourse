package com.senla.training.kononovich.server.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "authentification")
public class User extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158384486973004533L;
	
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @JsonCreator
    public User(@JsonProperty("id") Long id, @JsonProperty("login") String login, @JsonProperty("password") String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    @JsonCreator
    public User(@JsonProperty("login") String login,@JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }

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
