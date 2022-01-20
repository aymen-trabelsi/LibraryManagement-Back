package com.library.studentmanagement.service;
import com.library.studentmanagement.entity.Student;
import com.library.studentmanagement.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student s) {
        log.info("inside saveStudent method of StudentService");
        return studentRepository.save(s);
    }

    public Student findStudentById(Long studentId) {
        log.info("inside findStudent method of StudentService");
        return studentRepository.findStudentByStudentId(studentId);
    }

    public Iterable<Student> getAllStudents() {
        log.info("inside getAllStudents method of StudentService");
        return studentRepository.findAll();
    }

    public void deleteStudentById(Student student) {
        log.info("inside deleteStudentBy Id method of StudentService");
        Student s = studentRepository.findStudentByStudentId(student.getStudentId());
        s.setExist(0);
        studentRepository.save(s);
    }
}
