package com.example.springboot.service;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teach;
import com.example.springboot.entity.Teacher;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.TeachRepository;
import com.example.springboot.repository.TeacherRepository;
import com.example.springboot.request.TeachRequest;
import com.example.springboot.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeachService {

    @Autowired
    TeachRepository techRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public void addTech(TeachRequest request) {
        Optional<Lesson> lesson = lessonRepository.findById(request.getLessonId());
        Optional<Teacher> teacher = teacherRepository.findById(request.getTeacherId());
        if (lesson.isPresent() && teacher.isPresent()) {
            techRepository.save(new Teach(teacher.get(), lesson.get()));
        }
    }

    public List<TeacherResponse> getTeacherByLesson(Long lessonId) {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        Optional<Teach> teach = techRepository.findTeachByLesson(lesson.get());
//        Optional<Teacher> teacher = teacherRepository.findTeacherByTeach(teach.get());
        if (lesson.isPresent() && teach.isPresent()) {
            Optional<Teacher> teacher = teacherRepository.findTeacherByTeach(teach.get());
            TeacherResponse teacherResponse = new TeacherResponse(
                    teacher.get().getTeacherId(),
                    teacher.get().getTeacherName(),
                    teacher.get().getTeacherPhoneNumber()
            );
            teacherResponses.add(teacherResponse);
        }else{
            TeacherResponse teacherResponse = new TeacherResponse(
                    0l,
                    "待聘",
                    "無"
            );
            teacherResponses.add(teacherResponse);
        }
        return teacherResponses;
    }
}
