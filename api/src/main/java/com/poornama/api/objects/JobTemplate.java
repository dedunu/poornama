package com.poornama.api.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

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

	@ManyToOne
	@JoinColumn(name = "jobTypeId")
	private JobType jobType;

	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public JobType getJobType() {
		return jobType;
	}
	
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public BigDecimal getHireCharges() {
		return hireCharges;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public int getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(int containerSize) {
		this.containerSize = containerSize;
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

	public BigDecimal getHourlyDetentionCharges() {
		return hourlyDetentionCharges;
	}

	public void setHourlyDetentionCharges(BigDecimal hourlyDetentionCharges) {
		this.hourlyDetentionCharges = hourlyDetentionCharges;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
