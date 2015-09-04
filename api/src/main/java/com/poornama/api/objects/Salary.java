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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the basicComponent
	 */
	public BigDecimal getBasicComponent() {
		return basicComponent;
	}

	/**
	 * @param basicComponent the basicComponent to set
	 */
	public void setBasicComponent(BigDecimal basicComponent) {
		this.basicComponent = basicComponent;
	}

	/**
	 * @return the commissionComponent
	 */
	public BigDecimal getCommissionComponent() {
		return commissionComponent;
	}

	/**
	 * @param commissionComponent the commissionComponent to set
	 */
	public void setCommissionComponent(BigDecimal commissionComponent) {
		this.commissionComponent = commissionComponent;
	}

	/**
	 * @return the otherAllowances
	 */
	public BigDecimal getOtherAllowances() {
		return otherAllowances;
	}

	/**
	 * @param otherAllowances the otherAllowances to set
	 */
	public void setOtherAllowances(BigDecimal otherAllowances) {
		this.otherAllowances = otherAllowances;
	}

	/**
	 * @return the grossSalary
	 */
	public BigDecimal getGrossSalary() {
		return grossSalary;
	}

	/**
	 * @param grossSalary the grossSalary to set
	 */
	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}

	/**
	 * @return the netSalary
	 */
	public BigDecimal getNetSalary() {
		return netSalary;
	}

	/**
	 * @param netSalary the netSalary to set
	 */
	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}

}

