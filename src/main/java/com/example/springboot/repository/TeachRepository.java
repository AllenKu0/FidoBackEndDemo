package com.example.springboot.repository;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teach;
import com.example.springboot.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeachRepository extends JpaRepository<Teach, Long> {
    Optional<Teach> findTeachByLesson(Lesson lesson);

    Optional<Teach> findTeachByTeacher(Teacher teacher);
}
