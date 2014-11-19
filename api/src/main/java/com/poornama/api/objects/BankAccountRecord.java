package com.poornama.api.objects;

import javax.persistence.*;

/**
 * Created by dedunu on 11/11/14.
 */
@Entity
@Table
public class BankAccountRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private BankAccount bankAccount;

    public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
