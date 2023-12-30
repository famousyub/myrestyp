package com.example.crudmn.service;



import com.example.crudmn.models.StudentsExams;

import java.util.List;

public interface StudentExamsService {

    StudentsExams save(StudentsExams studentsExams);

    List<StudentsExams> getAll();

    void delete(StudentsExams studentsExams);

    void updateFinishedExam(String examId, String studentId);

    List<StudentsExams> getAssignExamByStudentID(String studentId);

}
