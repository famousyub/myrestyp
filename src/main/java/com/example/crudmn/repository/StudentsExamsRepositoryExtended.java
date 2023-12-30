package com.example.crudmn.repository;


import com.example.crudmn.models.StudentsExams;

import java.util.List;

public interface StudentsExamsRepositoryExtended {

    List<StudentsExams> getAssignExamByStudentID(String studentId);

    List<StudentsExams> getFinishedExamByStudentID(String studentId);

    void updateFinishedExam(String examId, String studentId);

}
