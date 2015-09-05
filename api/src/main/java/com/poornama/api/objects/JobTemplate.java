package com.poornama.api.objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author dedunu
 */
@Entity
@Table
public class JobTemplate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String displayName;
	
	private String fromLocation;
	
	private String toLocation;

	private int containerSize;

	private BigDecimal hireCharges;

	private BigDecimal labourCharges;

	private BigDecimal hourlyDetentionCharges;

	private BigDecimal dailyContainerCharges;

	private int freeHours;

	private int distance;

	@ManyToOne
	@JoinColumn(name = "jobTypeId")
	private JobType jobType;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "clientId")
	private Client client;

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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the fromLocation
	 */
	public String getFromLocation() {
		return fromLocation;
	}

	/**
	 * @param fromLocation the fromLocation to set
	 */
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	/**
	 * @return the toLocation
	 */
	public String getToLocation() {
		return toLocation;
	}

	/**
	 * @param toLocation the toLocation to set
	 */
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	/**
	 * @return the containerSize
	 */
	public int getContainerSize() {
		return containerSize;
	}

	/**
	 * @param containerSize the containerSize to set
	 */
	public void setContainerSize(int containerSize) {
		this.containerSize = containerSize;
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

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the jobType
	 */
	public JobType getJobType() {
		return jobType;
	}

	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	
}
