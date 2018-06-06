package com.example.webdevsummer12018.repositories;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer12018.models.ExamWidget;


public interface ExamWidgetRepository extends CrudRepository<ExamWidget, Integer> {
	
}
