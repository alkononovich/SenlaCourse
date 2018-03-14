package kononovich.vetpharmacy.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Queue extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @OneToMany(mappedBy = "vaccineQueue", fetch = FetchType.LAZY)
	 private List<User> users;
	 
	 @Column
	 private Date date;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	 

}
