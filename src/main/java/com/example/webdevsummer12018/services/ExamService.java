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

import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.MultipleChoiceQuestion;
import com.example.webdevsummer12018.models.Question;
import com.example.webdevsummer12018.models.TrueFalseQuestion;
import com.example.webdevsummer12018.models.ExamWidget;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.MultipleChoicesQuestionRepository;
import com.example.webdevsummer12018.repositories.QuestionRepository;
import com.example.webdevsummer12018.repositories.TrueFalseQuestionRepository;


import com.example.webdevsummer12018.models.FillInTheBlanksExamQuestion;

import com.example.webdevsummer12018.repositories.FillInTheBlanksExamQuestionRepository;

import com.example.webdevsummer12018.models.EssayExamQuestion;

import com.example.webdevsummer12018.repositories.EssayExamQuestionRepository;

import com.example.webdevsummer12018.repositories.ExamWidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoicesQuestionRepository mutiRepo;
	@Autowired
	EssayExamQuestionRepository essayExamQuestionRepository;
	@Autowired
	FillInTheBlanksExamQuestionRepository fillIntheBlanksExamQuestionRepository;
	@Autowired
	QuestionRepository questionRepository;
	

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = mutiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/essay/{questionId}")
	public EssayExamQuestion findEssayExamQuestion(@PathVariable("questionId") int questionId) {
		Optional<EssayExamQuestion> data = essayExamQuestionRepository.findById(questionId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/blanks/{id}")
	public FillInTheBlanksExamQuestion findFillInTheBlanksExamQuestion(@PathVariable("id") int id) {
		Optional<FillInTheBlanksExamQuestion> data = fillIntheBlanksExamQuestionRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueFalseQuestion createTrueOrFalseExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody TrueFalseQuestion newTrueOrFalseExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newTrueOrFalseExamQuestion.setExam(exam);
			return trueFalseRepository.save(newTrueOrFalseExamQuestion);
		}
		return null;		
	}
	
	@PostMapping("/api/exam/{examId}/multi")
	public MultipleChoiceQuestion createMultipleChoiceExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody MultipleChoiceQuestion newMultipleChoiceExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newMultipleChoiceExamQuestion.setExam(exam);
			return mutiRepo.save(newMultipleChoiceExamQuestion);
		}
		return null;		
	}
	
	@PostMapping("/api/exam/{eid}/essay")
	public EssayExamQuestion createEssayExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody EssayExamQuestion newEssayExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newEssayExamQuestion.setExam(exam);
			return essayExamQuestionRepository.save(newEssayExamQuestion);
		}
		return null;		
	}
	
	@PostMapping("/api/exam/{eid}/blanks")
	public FillInTheBlanksExamQuestion createFillInTheBlanksExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody FillInTheBlanksExamQuestion newFillInTheBlanksExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newFillInTheBlanksExamQuestion.setExam(exam);
			return fillIntheBlanksExamQuestionRepository.save(newFillInTheBlanksExamQuestion);
		}
		return null;		
	}
	
	@GetMapping("/api/exam/{examId}/truefalse")
	public List<Question> findAllTrueOrfalseQuestionsForExam(
			@PathVariable("examId") int examId) {
		List<Question> list = new ArrayList<Question>();
		List<Question> tlist = new ArrayList<Question>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
		}
			for(Question beq: tlist) {
				if(beq.getType().equals("TrueOrFalse"))
				{
					list.add(beq);
				}
				
			}
			
			return list;
			
	}
	
	@GetMapping("/api/exam/{examId}/multi")
	public List<Question> findAllMultipleChoiceExamQuestionsForExam(
			@PathVariable("examId") int examId) {
		List<Question> list = new ArrayList<Question>();
		List<Question> tlist = new ArrayList<Question>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
		}
			for(Question beq: tlist) {
				if(beq.getType().equals("MultipleChoice"))
				{
					list.add(beq);
				}
				
			}
			
			return list;		
	}
	
	@GetMapping("/api/exam/{eid}/essay")
	public List<Question> findAllEssayExamQuestionsForExam(
			@PathVariable("eid") int examId) {
		List<Question> list = new ArrayList<Question>();
		List<Question> tlist = new ArrayList<Question>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
		}
			for(Question beq: tlist) {
				if(beq.getType().equals("Essay"))
				{
					list.add(beq);
				}
				
			}
			
			return list;		
	}
	
	@GetMapping("/api/exam/{eid}/blanks")
	public List<Question> findAllFillInTheBlanksExamQuestionsForExam(
			@PathVariable("eid") int examId) {
		List<Question> list = new ArrayList<Question>();
		List<Question> tlist = new ArrayList<Question>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
		}
			for(Question beq: tlist) {
				if(beq.getType().equals("FillInTheBlanks"))
				{
					list.add(beq);
				}
				
			}
			
			return list;
			
	}
	
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/question")
	public Question createExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody Question newExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newExamQuestion.setExam(exam);
			return questionRepository.save(newExamQuestion);
		}
		return null;		
	}
	
	@DeleteMapping("/api/question/{qid}")
	public void deleteQuestion(@PathVariable("qid") int qid)
	{
		questionRepository.deleteById(qid);
	}
	
	@DeleteMapping("/api/truefalse/{qid}")
	public void deleteTrueOrfalseQuestion(@PathVariable("qid") int qid)
	{
		trueFalseRepository.deleteById(qid);
	}
	
	@DeleteMapping("/api/multi/{qid}")
	public void deleteMultipleChoiceExamQuestion(@PathVariable("qid") int qid)
	{
		mutiRepo.deleteById(qid);
	}
	
	@DeleteMapping("/api/essay/{id}")
	public void deleteEssayExamQuestion(@PathVariable("id") int id)
	{
		essayExamQuestionRepository.deleteById(id);
	}
	
	@DeleteMapping("/api/blanks/{id}")
	public void deleteFillInTheBlanksExamQuestion(@PathVariable("id") int id)
	{
		fillIntheBlanksExamQuestionRepository.deleteById(id);
	}
	
}