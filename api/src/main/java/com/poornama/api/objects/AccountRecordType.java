package com.poornama.api.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AccountRecordType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
}
