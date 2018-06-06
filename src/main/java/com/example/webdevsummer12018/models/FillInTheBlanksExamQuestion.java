package com.example.webdevsummer12018.models;

import javax.persistence.*;

@Entity
public class FillInTheBlanksExamQuestion extends Question{
	@Column(name = "VARIABLES", nullable = false)
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
}
