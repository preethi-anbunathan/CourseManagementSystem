package com.example.webdevsummer12018.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Course;
import com.example.webdevsummer12018.models.Module;
import com.example.webdevsummer12018.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseServices {
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	@CrossOrigin()
	public List<Course> findAllCourses() {
		return (List<Course>) courseRepository.findAll(); 
	}
	
	@PostMapping("/api/course")
	@ResponseBody
	public String createCourse(@RequestBody Course course, HttpServletResponse response) {
		Optional<Course> duplicateTitle = this.courseRepository.findCourseByTitle(course.getTitle());
		if(duplicateTitle.isPresent()) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return "{\"error\":\"A course already exists with that title\"}";
		}
		List<Module> modules = new ArrayList<Module>();
		course.setCreated(new Date());
		course.setModified(new Date());
		course.setModules(modules);
		courseRepository.save(course);
		return"{\"Success\":\"User Created!\"}" ;
		
		
	}
	
	@PutMapping("/api/course")
	@ResponseBody
	public String updateCourse(@RequestBody Course course, HttpServletResponse response) {
		Optional<Course> foundCourse = this.courseRepository.findById(course.getId());
		if(foundCourse.isPresent() == false) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "{\"error\":\"A course does not exist with that id\"}";
		}
		course.setModified(new Date());
		courseRepository.save(course);
		return"{\"Success\":\"User Updated!\"}" ;
		
		
	}
	
	@GetMapping("/api/course/{Id}")
	@ResponseBody
	public Course findCourseById(@PathVariable("Id") int id, HttpServletResponse response) {
		Optional<Course> course = courseRepository.findById(id);
		if(course.isPresent() == false) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return course.get();
	}
	
	@DeleteMapping("/api/course/{Id}")
	@ResponseBody
	public String deleteCourse(@PathVariable("Id") int id, HttpServletResponse response) {
		Optional<Course> course = courseRepository.findById(id);
		if(course.isPresent() == false) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "{\"error\":\"A course does not exist with that id\"}";
		}
		courseRepository.deleteById(id);
		return"{\"Success\":\"User Deleted\"}"; 
	}

}
