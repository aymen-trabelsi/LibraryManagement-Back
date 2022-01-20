package com.codewithGAS.livre.repository;
import com.codewithGAS.livre.entity.Emprunte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprunteRepository extends JpaRepository <Emprunte, Long> {

    List<Emprunte> getByStudentId(Long studentId);
}
