package com.poornama.api.objects;

import javax.persistence.*;

@Entity
@Table
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
}
