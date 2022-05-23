package com.example.springboot.repository;

import com.example.springboot.entity.CourseSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSelectionRepository extends JpaRepository<CourseSelection, Long> {
}
