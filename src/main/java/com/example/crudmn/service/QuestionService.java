package com.example.crudmn.service;



import com.example.crudmn.models.Question;

import java.util.List;

public interface QuestionService {

    Question save(Question question);

    List<Question> getAll();

    void delete(Question question);

    List<Question> getQuestionByTeacher(String teacherId);

    List<Question> getQuestionByStudent(String studentId);

    List<Question> findQuestionByExamId(String examId);
}
