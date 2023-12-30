package com.example.crudmn.repository;



import com.example.crudmn.models.Question;

import java.util.List;

public interface QuestionRepositoryExtended {

    List<Question> findQuestionByExamId(String examId);
}
