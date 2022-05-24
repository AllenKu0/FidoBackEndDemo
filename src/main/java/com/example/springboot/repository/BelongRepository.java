package com.example.springboot.repository;

import com.example.springboot.entity.Belong;
import com.example.springboot.entity.CourseSelection;
import com.example.springboot.entity.Teacher;
import com.example.springboot.request.TeacherRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BelongRepository extends JpaRepository<Belong, Long> {
    Optional<Belong> findClassRoomByTeacher(Teacher teacher);
}
