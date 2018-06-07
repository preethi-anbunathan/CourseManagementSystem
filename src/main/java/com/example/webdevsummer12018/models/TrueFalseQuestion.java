package com.example.webdevsummer12018.models;

import javax.persistence.Entity;

@Entity
public class TrueFalseQuestion extends Question {
	private boolean answer;

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
}