package kononovich.vetpharmacy.model;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Animal extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6289706185249186206L;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User owner;
    
    @Column 
    private String type;
    
    @Column
    private String name;
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Vaccine> vaccines;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
}
