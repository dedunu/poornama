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
	
	private String from;
	
	private String to;

	private BigDecimal hireCharges;

	private BigDecimal labourCharges;

	private BigDecimal containerCharges;

	private BigDecimal detentionCharges;

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
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public JobType getJobType() {
		return jobType;
	}
	
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
}
