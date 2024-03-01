package com.useregistration.service.Impl;

import com.useregistration.entity.Student;
import com.useregistration.exception.ResourceNotFound;
import com.useregistration.payload.StudentDto;
import com.useregistration.payload.StudentResponse;
import com.useregistration.repository.StudentRepository;
import com.useregistration.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public StudentDto saveStudent(StudentDto studentDto) {

        Student student =mapToEntity(studentDto);

        Student savedStudent = studentRepository.save(student);

        StudentDto dto = mapTODto(savedStudent);

        return dto;
    }
    StudentDto mapTODto(Student student) {
        StudentDto dto=modelMapper.map(student,StudentDto.class);
        return dto;
    }
    Student mapToEntity(StudentDto studentDto) {
        Student student =modelMapper.map(studentDto,Student.class);
        return student;
    }

    //    @Override
//    public void deleteStudent(long id) {
//        studentRepository.deleteById(id);
//
//    }
    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
    @Override
    public StudentDto updateStudent(long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Student is not found with is: " + id)
        );
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());

        Student updateStudent = studentRepository.save(student);

        StudentDto dto = mapTODto(updateStudent);
        return dto;
    }
    @Override
    public StudentDto getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Student is not found with is: " + id)
        );
        StudentDto dto = mapTODto(student);
        return dto;
    }
//    @Override                                                             "See All the student"
//    public List<StudentDto> getAllStudent() {
//        List<Student> students= studentRepository.findAll();
//        List<StudentDto> studentDtos= students.stream().map(student -> mapTODto(student)).collect(Collectors.toList());
//        return studentDtos;
//    }
    @Override
    public StudentResponse getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?       //ternary operation

                   //CONDISTION 1st(true)   :  CONDISTION 2nd(false)
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<Student> pageStudents= studentRepository.findAll(pageable);
        List<Student> students = pageStudents.getContent();

        List<StudentDto> studentDtos= students.stream().map(student -> mapTODto(student)).collect(Collectors.toList());

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentDto(studentDtos);
        studentResponse.setPageNo(pageStudents.getNumber());
        studentResponse.setPageSize(pageStudents.getSize());
        studentResponse.setTotalElement(pageStudents.getTotalElements());
        studentResponse.setLast(pageStudents.isLast());
        studentResponse.setTotalPages(pageStudents.getTotalPages());
        return studentResponse;
    }
}
