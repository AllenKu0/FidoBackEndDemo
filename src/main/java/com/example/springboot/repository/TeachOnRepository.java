package com.example.springboot.repository;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teach;
import com.example.springboot.entity.TeachOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeachOnRepository extends JpaRepository<TeachOn, Long> {
    Optional<TeachOn> findByLesson(Lesson lesson);
}
