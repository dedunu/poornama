package com.poornama.api.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String organizationName;
	
	private String address;
	
	private String telephoneNumber;
	
	private boolean client;
    
	@ManyToOne
    @JoinColumn(name = "organizationTypeId")
    private OrganizationType organizationType;
    
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

	public OrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}
	
}
