package com.example.springboot.repository;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository  extends JpaRepository<Lesson, Long> {
//    Optional<Lesson>
}
