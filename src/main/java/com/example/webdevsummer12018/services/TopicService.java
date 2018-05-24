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
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.repositories.CourseRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.ModuleRepository;
import com.example.webdevsummer12018.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
public class TopicService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	TopicRepository topicRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public Topic createTopic(@PathVariable("courseId") int courseId,@PathVariable("moduleId") int moduleId, @PathVariable("lessonId") int lessonId, @RequestBody Topic newTopic) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newTopic.setLesson(lesson);
			return topicRepository.save(newTopic);
		}
		return null;
	}
	
	@DeleteMapping("/api/topic/{topicId}")
	public void deleteTopic(@PathVariable("topicId") int topicId) {
		topicRepository.deleteById(topicId);
	}
	
	@GetMapping("/api/topic")
	public Iterable<Topic> findAllTopics() {
		return topicRepository.findAll();
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public List<Topic> findAllTopicForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopic();
		}
		throw new IllegalArgumentException("Invalid module");
	}
}