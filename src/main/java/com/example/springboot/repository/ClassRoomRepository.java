package com.example.springboot.repository;

import com.example.springboot.entity.ClassRoom;
import com.example.springboot.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    Optional<ClassRoom> findByClassName(String name);
}
