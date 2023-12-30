package com.example.crudmn.service;



import com.example.crudmn.models.Grade;

import java.util.List;

public interface GradeService {

    Grade save(Grade grade);

    List<Grade> getAll();

    void delete(Grade grade);

    Grade findById(String id);

    List<Grade> getGradeByStudentAndExam(String studentId, String examId);

}
