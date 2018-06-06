package com.example.webdevsummer12018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
