package com.example.springboot.repository;

import com.example.springboot.entity.CourseSelection;
import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseSelectionRepository extends JpaRepository<CourseSelection, Long> {
    Optional<CourseSelection> findCourseSelectionByUserAndLesson(User user, Lesson lesson);

    Optional<List<CourseSelection>> findCourseSelectionByUser(User user);
}
