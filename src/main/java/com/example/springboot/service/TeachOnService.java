package com.example.springboot.service;

import com.example.springboot.entity.*;
import com.example.springboot.repository.*;
import com.example.springboot.request.TeachOnRequest;
import com.example.springboot.request.TeacherRequest;
import com.example.springboot.response.ClassRoomResponse;
import com.example.springboot.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeachOnService {
    @Autowired
    private TeachOnRepository teachOnRepository;

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    public void saveTeachOn(TeachOnRequest teachOnRequest) {
        Optional<Lesson> lesson = lessonRepository.findById(teachOnRequest.getLessonId());
        Optional<ClassRoom> classRoom = classRoomRepository.findById(teachOnRequest.getClassRoomId());
        if(lesson.isPresent() && classRoom.isPresent()){
            TeachOn teachOn = new TeachOn(classRoom.get(),lesson.get());
            teachOnRepository.save(teachOn);
        }
    }

    public List<ClassRoomResponse> getClassRoomByLesson(Long lessonId) {
        List<ClassRoomResponse> classRoomResponses = new ArrayList<>();
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        Optional<TeachOn> teachOn = teachOnRepository.findByLesson(lesson.get());
        if (lesson.isPresent() && teachOn.isPresent()) {
            Optional<ClassRoom> classRoom = classRoomRepository.findById(teachOn.get().getClassRoom().getClassId());
            ClassRoomResponse classRoomResponse = new ClassRoomResponse(
                    classRoom.get().getClassId(),
                    classRoom.get().getClassName(),
                    classRoom.get().getClassLocation(),
                    classRoom.get().getClassPhoneNumber()
            );
            classRoomResponses.add(classRoomResponse);
        }else{
            ClassRoomResponse classRoomResponse = new ClassRoomResponse(
                    0l,
                   "未指定",
                    "未指定",
                    "無"
            );
            classRoomResponses.add(classRoomResponse);
        }
        return classRoomResponses;
    }

    public void deleteTeachOn(TeachOnRequest teachOnRequest){
        Optional<Lesson> lesson = lessonRepository.findById(teachOnRequest.getLessonId());
        Optional<TeachOn> teachOn = teachOnRepository.findByLesson(lesson.get());
        if(teachOn.isPresent()){
            teachOnRepository.delete(teachOn.get());
        }
    }
}
