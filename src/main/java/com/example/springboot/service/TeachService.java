package com.example.springboot.service;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teach;
import com.example.springboot.entity.Teacher;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.TeacherRepository;
import com.example.springboot.repository.TeachRepository;
import com.example.springboot.request.TeachRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeachService {

    @Autowired
    TeachRepository techRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    TeacherRepository teacherRepository;
    public void addTech(TeachRequest request){
        Optional<Lesson> lesson = lessonRepository.findById(request.getLessonId());
        Optional<Teacher> teacher = teacherRepository.findById(request.getTeacherId());
        if(lesson.isPresent() && teacher.isPresent()) {
            techRepository.save(new Teach(teacher.get(), lesson.get()));
        }
    }
}
