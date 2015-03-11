package com.poornama.api.objects;

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
	private Employee cleanerId;
	
	private Date date;
	
	private float surcharges;
	
	
	
	public int getId() {
		return id;
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
