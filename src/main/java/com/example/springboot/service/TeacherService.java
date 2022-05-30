package com.example.springboot.service;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teach;
import com.example.springboot.entity.Teacher;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.TeachRepository;
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

    @Autowired
    private TeachRepository teachRepository;

    public void saveTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher(teacherRequest.getTeacher_name());
        teacherRepository.save(teacher);
    }

    public List<TeacherResponse> getAllTeacher() {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        String className = "";
        for (Teacher teacher : teacherRepository.findAll()) {
            Optional<Teach> teach = teachRepository.findTeachByTeacher(teacher);

            if(teacher.getBelong() != null){
                className = teacher.getBelong().getClassRoom().getClassName();
            }else{
                className = "未指定";
            }
            if(teach.isPresent()){
                TeacherResponse response = new TeacherResponse(
                        teacher.getTeacherId()
                        , teacher.getTeacherName()
                        , teach.get().getLesson().getLessonName()
                        , className
                );
                teacherResponses.add(response);
            }else{
                TeacherResponse response = new TeacherResponse(
                        teacher.getTeacherId()
                        , teacher.getTeacherName()
                        ,"未分派"
                        , className
                );
                teacherResponses.add(response);
            }
        }
        return teacherResponses;
    }

    public void deleteTeacher(TeacherRequest teacherRequest){
        Optional<Teacher> teacher = teacherRepository.findTeacherByTeacherName(teacherRequest.getTeacher_name());
        if(teacher.isPresent()){
            teacherRepository.delete(teacher.get());
        }
    }
}

