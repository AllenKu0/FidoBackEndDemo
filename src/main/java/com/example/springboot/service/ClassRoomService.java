package com.example.springboot.service;

import com.example.springboot.entity.*;
import com.example.springboot.repository.ClassRoomRepository;
import com.example.springboot.request.ClassRoomRequest;
import com.example.springboot.response.ClassRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {
    @Autowired
    ClassRoomRepository classRoomRepository;

    public void saveClassRoom(ClassRoomRequest classRoomRequest){
        ClassRoom classRoom = new ClassRoom(classRoomRequest.getClassName());
        classRoomRepository.save(classRoom);
    }

    public List<ClassRoomResponse> getAllClassRoom(){
        List<ClassRoomResponse> classRoomResponses = new ArrayList<>();
        for (ClassRoom classRoom : classRoomRepository.findAll()) {
            ClassRoomResponse response = new ClassRoomResponse(
                    classRoom.getClassId()
                    , classRoom.getClassName()
            );
            classRoomResponses.add(response);
        }
        return classRoomResponses;
    }

    public void deleteClassRoom(ClassRoomRequest classRoomRequest){
        Optional<ClassRoom> classRoom = classRoomRepository.findByClassName(classRoomRequest.getClassName());
        if(classRoom.isPresent()){
            classRoomRepository.delete(classRoom.get());
        }
    }
}
