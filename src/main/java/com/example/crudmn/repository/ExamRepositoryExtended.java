package com.example.crudmn.repository;

import com.example.crudmn.models.Exam;

import java.util.List;

public interface ExamRepositoryExtended {

    List<Exam> getExamByTeacher(String teacherId);

    List<Exam> getAssignExamByStudentID(String studentId);

    List<Exam> getFinishedExamByStudentID(String studentId);

    Exam findExamById(String id);
}