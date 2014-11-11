package com.poornama.api.objects;

import javax.persistence.*;

/**
 * Created by dedunu on 11/11/14.
 */
@Entity
@Table(name = "BankAccount", uniqueConstraints = @UniqueConstraint(columnNames = {"bankName", "accountNumber"}))
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String bankName;
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "bankAccountTypeId")
    private BankAccountType bankAccountType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }
}
