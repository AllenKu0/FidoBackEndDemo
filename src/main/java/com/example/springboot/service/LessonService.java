package com.example.springboot.service;

import com.example.springboot.entity.Lesson;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.response.ListLessonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public ListLessonResponse getAllLesson(){

        List< ListLessonResponse.LessonResponse> lessonResponseList=new ArrayList<>();
        for (int i=0;i<lessonRepository.findAll().size();i++){
            Lesson lesson= lessonRepository.findAll().get(i);
            ListLessonResponse.LessonResponse response=new ListLessonResponse.LessonResponse(
                    i,
                   lesson.getLesson_name(),
                    lesson.getLesson_credit()
            );
            lessonResponseList.add(response);
        }
        return new ListLessonResponse(lessonResponseList);
    }
}
