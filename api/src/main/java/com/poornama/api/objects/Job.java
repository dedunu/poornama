package com.poornama.api.objects;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "jobTemplateId")
	private JobTemplate jobTemplate;
	
	@ManyToOne
	@JoinColumn(name = "driverId")
	private Employee driver;
	
	@ManyToOne
	@JoinColumn(name = "cleanerId")
	private Employee cleaner;
	
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;
	
	private Date startDate;

	private Date endDate;

	private BigDecimal hireCharges;

	private BigDecimal labourCharges;

	private BigDecimal containerCharges;

	private BigDecimal detentionCharges;

	private BigDecimal dailyContainerCharges;

	private int freeHours;

	public int getId() {
		return id;
	}

	public Employee getDriver() {
		return driver;
	}

	public void setDriver(Employee driver) {
		this.driver = driver;
	}

	public Employee getCleaner() {
		return cleaner;
	}

	public void setCleaner(Employee cleaner) {
		this.cleaner = cleaner;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getHireCharges() {
		return hireCharges;
	}

	public void setHireCharges(BigDecimal hireCharges) {
		this.hireCharges = hireCharges;
	}

	public BigDecimal getLabourCharges() {
		return labourCharges;
	}

	public void setLabourCharges(BigDecimal labourCharges) {
		this.labourCharges = labourCharges;
	}

	public BigDecimal getContainerCharges() {
		return containerCharges;
	}

	public void setContainerCharges(BigDecimal containerCharges) {
		this.containerCharges = containerCharges;
	}

	public BigDecimal getDetentionCharges() {
		return detentionCharges;
	}

	public void setDetentionCharges(BigDecimal detentionCharges) {
		this.detentionCharges = detentionCharges;
	}

	public BigDecimal getDailyContainerCharges() {
		return dailyContainerCharges;
	}

	public void setDailyContainerCharges(BigDecimal dailyContainerCharges) {
		this.dailyContainerCharges = dailyContainerCharges;
	}

	public int getFreeHours() {
		return freeHours;
	}

	public void setFreeHours(int freeHours) {
		this.freeHours = freeHours;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public JobTemplate getJobTemplate() {
		return jobTemplate;
	}
	
	public void setJobTemplate(JobTemplate jobTemplate) {
		this.jobTemplate = jobTemplate;
	}

}
