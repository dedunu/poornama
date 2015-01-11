package com.poornama.api.objects;

import javax.persistence.*;

/**
 * Created by dedunu on 11/11/14.
 */
@Entity
@Table(name = "Account", uniqueConstraints = @UniqueConstraint(columnNames = {
		"organizationId", "accountNumber" }))
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "organizationId")
	private Organization organization;
	private String accountNumber;
	@ManyToOne
	@JoinColumn(name = "accountTypeId")
	private AccountType accountType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Organization getBank() {
		return organization;
	}

	public void setBank(Organization organization) {
		this.organization = organization;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getBankAccountType() {
		return accountType;
	}

	public void setBankAccountType(AccountType bankAccountType) {
		this.accountType = bankAccountType;
	}
}
