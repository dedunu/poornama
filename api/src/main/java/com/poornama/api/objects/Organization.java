package com.poornama.api.objects;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String organizationName;
	
	private String address;
	
	private String telephoneNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

}
