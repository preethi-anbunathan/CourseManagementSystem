package com.example.webdevsummer12018.models;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Column(unique=true)
	private String title;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="course")
	private List<Module> modules;
	
	//Return Id of course
	public int getId() {
		return id;
	}
	
	//Sets Id of course
	public void setId(int id) {
		this.id = id;
	}
	
	//Return title of course
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	//Return date that course is created on
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	//Return date that course is last modified on
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	}

