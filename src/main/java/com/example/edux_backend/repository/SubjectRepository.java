package com.example.edux_backend.repository;

import com.example.edux_backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsBySubjectName(String userName);
    List<Subject> findBySubjectName(String subjectName);
}
