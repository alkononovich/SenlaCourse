package kononovich.vetpharmacy.model;

import javax.persistence.*;

@Entity
@Table
public class UserCreds extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -90309074164494901L;
	
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;
    
	public UserCreds() {
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
