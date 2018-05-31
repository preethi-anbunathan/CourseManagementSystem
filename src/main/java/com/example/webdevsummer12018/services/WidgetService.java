package com.example.webdevsummer12018.services;
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.TopicRepository;
import com.example.webdevsummer12018.repositories.WidgetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins= "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets(){
		
		return (List<Widget>) widgetRepository.findAll();
		
	}
	
	@PostMapping("/api/widget/save/{topicId}")
	public List<Widget> saveAllWidgets(@RequestBody List<Widget> widgets, @PathVariable("topicId") int topicId) {
		Optional<Topic> topicData = topicRepository.findById(topicId);
		List<Widget> response = new ArrayList<Widget>();
		if (topicData.isPresent()) {
			List<Widget> widList = topicData.get().getWidgets();
			widgetRepository.deleteAll(widList);
			for (Widget w : widgets) {
				w.setTopic(topicData.get());
				response.add(widgetRepository.save(w));
			}
			return response;
		} else {
			return new ArrayList<Widget>();
		}
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic course = data.get();
			return course.getWidgets();
		}
		return null;		
	}
	
	@PostMapping("/api/topic/{topicId}/widget")
	public Widget createWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody Widget newWidget) {
		Optional<Topic> data = topicRepository.findById(topicId);
		
		if(data.isPresent()) {
			Topic topic = data.get();
			newWidget.setTopic(topic);
			return widgetRepository.save(newWidget);

		}
		return null;		
	}
	
	@PostMapping("/api/topic/{topicId}/widgets")
	public List<Widget> createWidgets(
			@PathVariable("topicId") int topicId,
			@RequestBody List<Widget> newWidgets) {
		widgetRepository.deleteWidgetsByLessonId(topicId);
		List<Widget> output = new ArrayList<Widget>();
	for(Widget w : newWidgets) {
		output.add(createWidget(topicId,w));
	}
		return output;
	}
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(
			@PathVariable("widgetId") int widgetId,
			@RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			Widget widget = data.get();
			widget.setName(newWidget.getName());
			widget.setWidgetOrder(newWidget.getWidgetOrder());
			widget.setText(newWidget.getText());
			widget.setSize(newWidget.getSize());
			widget.setListType(newWidget.getListType());
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setLinkName(newWidget.getLinkName());
			widgetRepository.save(widget);
			return widget;
		}
		return null;		
	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId)
	{	
		widgetRepository.deleteById(widgetId);
	}

}
