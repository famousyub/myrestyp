package com.example.crudmn.service;



import com.example.crudmn.models.QuestionChoices;
import com.example.crudmn.repository.QuestionChoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionChoicesServiceImpl implements QuestionChoicesService {

    @Autowired
    private QuestionChoicesRepository questionChoicesRepository;

    @Override
    public QuestionChoices getChoiceByIdAndExamIdandQuestionId(String id, String examId, String questionId) {
        return questionChoicesRepository.getChoiceByIdAndExamIdandQuestionId(id, examId, questionId);
    }
}
