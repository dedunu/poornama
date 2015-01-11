package com.poornama.api.objects;

import javax.persistence.*;

/**
 * Created by dedunu on 11/11/14.
 */
@Entity
@Table
public class AccountRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name = "accountId")
	private Account account;

	public Account getBankAccount() {
		return account;
	}

	public void setBankAccount(Account account) {
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
