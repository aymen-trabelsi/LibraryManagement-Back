package com.library.studentmanagement.repository;
import com.library.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository  <Student, Long> {

    Student findStudentByStudentId(Long studentId);

    Student findStudentByEmail(String email);
}
