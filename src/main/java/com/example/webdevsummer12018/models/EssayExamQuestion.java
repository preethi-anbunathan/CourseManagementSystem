package com.example.webdevsummer12018.models;

import javax.persistence.Entity;

@Entity
public class EssayExamQuestion extends Question{

	private String essayAnswer;

	public String getEssayAnswer() {
		return essayAnswer;
	}

	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}
}
