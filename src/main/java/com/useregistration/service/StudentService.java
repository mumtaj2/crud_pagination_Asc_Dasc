package com.useregistration.service;

import com.useregistration.payload.StudentDto;
import com.useregistration.payload.StudentResponse;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    void deleteAllStudents();

    StudentDto updateStudent(long id, StudentDto studentDto);

    StudentDto getStudentById(long id);

    StudentResponse getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir);

    //   void deleteStudent(long id);
}
