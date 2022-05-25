package com.example.springboot.service;

import com.example.springboot.entity.*;
import com.example.springboot.repository.BelongRepository;
import com.example.springboot.repository.ClassRoomRepository;
import com.example.springboot.repository.TeacherRepository;
import com.example.springboot.request.*;
import com.example.springboot.response.ClassRoomResponse;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BelongService {
    @Autowired
    BelongRepository belongRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassRoomRepository classRoomRepository;

    public ClassRoomResponse getClassRoomByTeacherId(BelongRequest belongRequest){
        ClassRoomResponse classroomRequests = null;
        Optional<Teacher> teacher = teacherRepository.findById(belongRequest.getTeacher_id());

        if(teacher.isPresent()){
            if(belongRepository.findClassRoomByTeacher(teacher.get()).isPresent()){
                ClassRoom classRoom = belongRepository.findClassRoomByTeacher(teacher.get()).get().getClassRoom();
                classroomRequests = new ClassRoomResponse(classRoom.getClassId(),classRoom.getClassName());
            }
        }
        return classroomRequests;
    }

    public void saveBelong(BelongPostRequest belongPostRequest){
        Optional<ClassRoom> classRoom = classRoomRepository.findById(belongPostRequest.getClassRoom_id());
        Optional<Teacher> teacher = teacherRepository.findById(belongPostRequest.getTeacher_id());

        Belong belong = new Belong(classRoom.get(),teacher.get());
        belongRepository.save(belong);

    }

    public void deleteBelong(BelongRequest belongRequest){
        Optional<Teacher> teacher = teacherRepository.findById(belongRequest.getTeacher_id());

        if(teacher.isPresent()){
            if(belongRepository.findClassRoomByTeacher(teacher.get()).isPresent()){
                Optional<Belong> belong=belongRepository.findClassRoomByTeacher(teacher.get());
                belongRepository.delete(belong.get());
            }
        }
    }
}
