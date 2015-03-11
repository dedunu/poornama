package com.poornama.api.objects;

import javax.persistence.*;

/**
 * Created by dedunu on 11/11/14.
 */
@Entity
@Table(name = "Account", uniqueConstraints = @UniqueConstraint(columnNames = {
		"organizationId", "accountName" }))
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "organizationId")
	private Organization organization;
    
	@Column(name = "accountName")
	private String accountName;
	
	@ManyToOne
	@JoinColumn(name = "accountTypeId")
	private AccountType accountType;
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public AccountType getBankAccountType() {
		return accountType;
	}

	public void setBankAccountType(AccountType bankAccountType) {
		this.accountType = bankAccountType;
	}
}
