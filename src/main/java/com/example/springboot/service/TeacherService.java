package com.example.springboot.service;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teacher;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.TeacherRepository;
import com.example.springboot.request.TeacherRequest;
import com.example.springboot.response.ListLessonResponse;
import com.example.springboot.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public void saveTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher(teacherRequest.getTeacher_name());
        teacherRepository.save(teacher);
    }

    public List<TeacherResponse> getAllTeacher() {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (Teacher teacher : teacherRepository.findAll()) {
            TeacherResponse response = new TeacherResponse(
                    teacher.getTeacher_id()
                    , teacher.getTeacher_name()
            );
            teacherResponses.add(response);
        }
        return teacherResponses;
    }

//    public void deleteTeacher(TeacherRequest teacherRequest){
//        Optional<Teacher> teacher = teacherRepository.findTeacherByName(teacherRequest.getTeacher_name());
//        if(teacher.isPresent()){
//            teacherRepository.delete(teacher.get());
//        }
//    }
}

