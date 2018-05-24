package com.example.webdevsummer12018.services;

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
import com.example.webdevsummer12018.models.Module;
import com.example.webdevsummer12018.repositories.CourseRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId, @RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int lessonId) {
		lessonRepository.deleteById(lessonId);
	}
	
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll();
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		throw new IllegalArgumentException("Invalid module");
	}
}
