package com.example.webdevsummer12018.models;

import javax.persistence.*;

@Entity
public class Assignment extends ExamWidget {
	private int points=0;
	 private String essayAnswer;
	 private String uploadFileLink;
	 private String link;
	 private String title;
	 private String description;

	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getEssayAnswer() {
		return essayAnswer;
	}
	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}
	public String getUploadFileLink() {
		return uploadFileLink;
	}
	public void setUploadFileLink(String uploadFileLink) {
		this.uploadFileLink = uploadFileLink;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 
}
