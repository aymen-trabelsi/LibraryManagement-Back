package com.library.studentmanagement.controller;

import com.library.studentmanagement.entity.Student;
import com.library.studentmanagement.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
@CrossOrigin(allowedHeaders = "**", origins = "http://localhost:4200")
public class StudentController {

    @Autowired
    private StudentService studentService ;

    @PostMapping("/save")
    public Student saveStudent (@RequestBody Student student){
        log.info("inside saveStudent method of StudentController");
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable("id") Long studentId) {
        log.info("inside findStudent method of StudentController");
        return studentService.findStudentById(studentId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public Iterable<Student> getAllStudents (){
        log.info("inside getAllStudents method of StudentController");
        return studentService.getAllStudents();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/delete")
    public void deleteStudentById(@RequestBody Student student){
        log.info("inside deleteStudentById method of StudentController");
        studentService.deleteStudentById(student);
    }

}
