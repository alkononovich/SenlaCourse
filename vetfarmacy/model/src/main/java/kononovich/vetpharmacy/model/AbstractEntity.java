package kononovich.vetpharmacy.model;

import java.io.Serializable;

import javax.persistence.*;


@MappedSuperclass
public class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3350201758611700505L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Long id;

    protected AbstractEntity() {
    }


    public void setId(Long id) {
        this.id = id;
    }



    public Long getId() {
        return id;
    }

}
