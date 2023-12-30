package com.example.crudmn.service;

import com.example.crudmn.models.StudentsExams;
import com.example.crudmn.repository.StudentsExamsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentExamsServiceImpl implements StudentExamsService{

    @Autowired
    private StudentsExamsRepository studentsExamsRepository;

    @Override
    public StudentsExams save(StudentsExams studentsExams) {
        return studentsExamsRepository.save(studentsExams);
    }

    @Override
    public List<StudentsExams> getAll() {
        return null;
    }

    @Override
    public void delete(StudentsExams studentsExams) {

    }

    @Override
    public void updateFinishedExam(String examId, String studentId) {
         studentsExamsRepository.updateFinishedExam(examId, studentId);
    }

    @Override
    public List<StudentsExams> getAssignExamByStudentID(String studentId) {
        return studentsExamsRepository.getAssignExamByStudentID(studentId);
    }
}
