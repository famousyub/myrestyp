package com.example.crudmn.repository;


import com.example.crudmn.models.QuestionChoices;

public interface QuestionChoicesRepositoryExtended {

    QuestionChoices getChoiceByIdAndExamIdandQuestionId(String id, String examId, String questionId);
}
