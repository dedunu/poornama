package com.poornama.api.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dedunu
 */
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

	private BigDecimal hourlyDetentionCharges;

	private BigDecimal dailyContainerCharges;

	private int freeHours;

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
	 * @return the jobTemplate
	 */
	public JobTemplate getJobTemplate() {
		return jobTemplate;
	}

	/**
	 * @param jobTemplate the jobTemplate to set
	 */
	public void setJobTemplate(JobTemplate jobTemplate) {
		this.jobTemplate = jobTemplate;
	}

	/**
	 * @return the driver
	 */
	public Employee getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Employee driver) {
		this.driver = driver;
	}

	/**
	 * @return the cleaner
	 */
	public Employee getCleaner() {
		return cleaner;
	}

	/**
	 * @param cleaner the cleaner to set
	 */
	public void setCleaner(Employee cleaner) {
		this.cleaner = cleaner;
	}

	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the hireCharges
	 */
	public BigDecimal getHireCharges() {
		return hireCharges;
	}

	/**
	 * @param hireCharges the hireCharges to set
	 */
	public void setHireCharges(BigDecimal hireCharges) {
		this.hireCharges = hireCharges;
	}

	/**
	 * @return the labourCharges
	 */
	public BigDecimal getLabourCharges() {
		return labourCharges;
	}

	/**
	 * @param labourCharges the labourCharges to set
	 */
	public void setLabourCharges(BigDecimal labourCharges) {
		this.labourCharges = labourCharges;
	}

	/**
	 * @return the containerCharges
	 */
	public BigDecimal getContainerCharges() {
		return containerCharges;
	}

	/**
	 * @param containerCharges the containerCharges to set
	 */
	public void setContainerCharges(BigDecimal containerCharges) {
		this.containerCharges = containerCharges;
	}

	/**
	 * @return the detentionCharges
	 */
	public BigDecimal getDetentionCharges() {
		return detentionCharges;
	}

	/**
	 * @param detentionCharges the detentionCharges to set
	 */
	public void setDetentionCharges(BigDecimal detentionCharges) {
		this.detentionCharges = detentionCharges;
	}

	/**
	 * @return the hourlyDetentionCharges
	 */
	public BigDecimal getHourlyDetentionCharges() {
		return hourlyDetentionCharges;
	}

	/**
	 * @param hourlyDetentionCharges the hourlyDetentionCharges to set
	 */
	public void setHourlyDetentionCharges(BigDecimal hourlyDetentionCharges) {
		this.hourlyDetentionCharges = hourlyDetentionCharges;
	}

	/**
	 * @return the dailyContainerCharges
	 */
	public BigDecimal getDailyContainerCharges() {
		return dailyContainerCharges;
	}

	/**
	 * @param dailyContainerCharges the dailyContainerCharges to set
	 */
	public void setDailyContainerCharges(BigDecimal dailyContainerCharges) {
		this.dailyContainerCharges = dailyContainerCharges;
	}

	/**
	 * @return the freeHours
	 */
	public int getFreeHours() {
		return freeHours;
	}

	/**
	 * @param freeHours the freeHours to set
	 */
	public void setFreeHours(int freeHours) {
		this.freeHours = freeHours;
	}
}
