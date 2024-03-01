package com.useregistration.controller;

import com.useregistration.payload.StudentDto;
import com.useregistration.payload.StudentResponse;
import com.useregistration.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto ){
        StudentDto dto = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
//        studentService.deleteStudent(id);
//        return new ResponseEntity<>("Student is deleted", HttpStatus.OK);
//    }
@DeleteMapping("/deleteAll")
public ResponseEntity<String> deleteAllStudents() {
    studentService.deleteAllStudents();
    return new ResponseEntity<>("All Students are deleted", HttpStatus.OK);
}
@PutMapping("/{id}")
public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto,@PathVariable("id") long id ){
    StudentDto dto = studentService.updateStudent(id, studentDto);
    return new ResponseEntity<>(dto,HttpStatus.OK);
    }
@GetMapping("/{id}")
    public ResponseEntity <StudentDto> getStudentById(@PathVariable("id") long id){
       StudentDto dto= studentService.getStudentById(id);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }
//    @GetMapping                                 "See All record on this Code"
//    public List<StudentDto> getAllStudent(){
//    List<StudentDto> studentDto = studentService.getAllStudent();
//    return studentDto;

    @GetMapping   //Http:localhost:9090/api/student?pageNo=1&pageSize=3&sortBy=title
    public StudentResponse getAllStudent(
            @RequestParam(value = "pageNo",   defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false)int pageSize,
            @RequestParam(value = "sortBy",   defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir",   defaultValue = "asc", required = false) String sortDir

               ) {
        StudentResponse studentResponse =studentService.getAllStudent(pageNo, pageSize,sortBy,sortDir);
        return studentResponse;
    }
}


