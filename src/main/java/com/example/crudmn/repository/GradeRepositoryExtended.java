package com.example.crudmn.repository;





import com.example.crudmn.models.Grade;

import java.util.List;

public interface GradeRepositoryExtended {

    List<Grade> getGradeByStudentAndExam(String studentId, String examId);

}
