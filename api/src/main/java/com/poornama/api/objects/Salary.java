package com.poornama.api.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dedunu
 */
@Entity
@Table(name = "Salary", uniqueConstraints = @UniqueConstraint(columnNames = {"employeeId", "date"}))
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	private Date date;

	private BigDecimal basicComponent;

	private BigDecimal commissionComponent;

	private BigDecimal otherAllowances;

	private BigDecimal grossSalary;

	private BigDecimal netSalary;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public BigDecimal getBasicComponent() {
		return basicComponent;
	}

	public void setBasicComponent(BigDecimal basicComponent) {
		this.basicComponent = basicComponent;
	}

	public BigDecimal getCommissionComponent() {
		return commissionComponent;
	}

	public void setCommissionComponent(BigDecimal commissionComponent) {
		this.commissionComponent = commissionComponent;
	}

	public BigDecimal getOtherAllowances() {
		return otherAllowances;
	}

	public void setOtherAllowances(BigDecimal otherAllowances) {
		this.otherAllowances = otherAllowances;
	}

	public BigDecimal getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}

	public BigDecimal getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}
}

