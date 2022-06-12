package com.example.springboot.service;

import com.example.springboot.entity.*;
import com.example.springboot.repository.BelongRepository;
import com.example.springboot.repository.ClassRoomRepository;
import com.example.springboot.repository.OfficeRepository;
import com.example.springboot.repository.TeacherRepository;
import com.example.springboot.request.*;
import com.example.springboot.response.ClassRoomResponse;
import com.example.springboot.response.OfficeResponse;
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
    OfficeRepository officeRepository;

    public OfficeResponse getClassRoomByTeacherId(String teacherName){
        OfficeResponse officeResponse = null;
        Optional<Teacher> teacher = teacherRepository.findTeacherByTeacherName(teacherName);
        if(teacher.isPresent()){
            if(belongRepository.findClassRoomByTeacher(teacher.get()).isPresent()){
                Office office = belongRepository.findClassRoomByTeacher(teacher.get()).get().getOffice();
                officeResponse = new OfficeResponse(office.getOfficeId(),office.getOfficeName(),office.getOfficePhoneNumber());
            }
        }
        return officeResponse;
    }

    public void saveBelong(BelongPostRequest belongPostRequest){
        Optional<Office> office = officeRepository.findById(belongPostRequest.getOffice_id());
        Optional<Teacher> teacher = teacherRepository.findById(belongPostRequest.getTeacher_id());

        Belong belong = new Belong(office.get(),teacher.get());
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
