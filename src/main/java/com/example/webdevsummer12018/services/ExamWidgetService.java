package com.example.webdevsummer12018.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.ExamWidget;
import com.example.webdevsummer12018.repositories.LessonRepository;


import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.ExamWidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamWidgetService {
	@Autowired
	ExamWidgetRepository repository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ExamRepository examRepository;
	
	
	@GetMapping("/api/lesson/{lessonId}/examwidget")
	public List<ExamWidget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			return lesson.getExamWidget();
			
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<ExamWidget> findAllExamsForLesson(
			@PathVariable("lessonId") int lessonId) {
		List<ExamWidget> list = new ArrayList<ExamWidget>();
		List<ExamWidget> wlist = new ArrayList<ExamWidget>();
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			wlist.addAll(lesson.getExamWidget()) ;
		}
			for(ExamWidget ew: wlist) {
				if(ew.getWidgetType().equals("Exam"))
				{
					list.add(ew);
				}
				
			}
			
			return list;
			
		
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);

		if(data.isPresent()) {
			Lesson lesson = data.get();
			newExam.setLesson(lesson);
			return examRepository.save(newExam);
		}
		return null;		
	}
	
	
	@DeleteMapping("/api/exam/{eid}")
	public void deleteExam(@PathVariable("eid") int eid)
	{
		examRepository.deleteById(eid);
	}
	
	
	@PostMapping("/api/examwidget/save")
	public void saveAllWidgets(@RequestBody
			List<ExamWidget> widgets) {
		repository.deleteAll();
		for(ExamWidget widget: widgets) {
			repository.save(widget);
		}
	}
	
	@GetMapping("/api/examwidget")
	public List<ExamWidget> findAllWidgets() {
		return (List<ExamWidget>) repository.findAll();
	}
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{eid}")
	public Exam findExamById(@PathVariable("eid") int eid) {
		Optional<Exam> data = examRepository.findById(eid);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
}