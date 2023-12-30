package com.example.crudmn.service;


import com.example.crudmn.models.QuestionChoices;

public interface QuestionChoicesService {

    QuestionChoices getChoiceByIdAndExamIdandQuestionId(String id, String examId, String questionId);
}
