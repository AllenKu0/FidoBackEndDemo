package com.example.springboot.service;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teach;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.TeachRepository;
import com.example.springboot.request.LessonRequest;
import com.example.springboot.response.ListLessonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TeachRepository teachRepository;

    public void saveLesson(LessonRequest lessonRequest){
        Lesson lesson=new Lesson(lessonRequest.getLesson_name(),lessonRequest.getLesson_credit());
        lessonRepository.save(lesson);

    }
    public ListLessonResponse getAllLesson(){

        List< ListLessonResponse.LessonResponse> lessonResponseList=new ArrayList<>();
        for (int i=0;i<lessonRepository.findAll().size();i++){
            Lesson lesson= lessonRepository.findAll().get(i);
            Optional<Teach> teach = teachRepository.findTeachByLesson(lesson);
            if(teach.isPresent()){
                ListLessonResponse.LessonResponse response=new ListLessonResponse.LessonResponse(
                        i,
                        lesson.getLessonName(),
                        lesson.getLessonCredit(),
                        teach.get().getTeacher().getTeacherName()
                );
                lessonResponseList.add(response);
            }else {
                ListLessonResponse.LessonResponse response = new ListLessonResponse.LessonResponse(
                        i,
                        lesson.getLessonName(),
                        lesson.getLessonCredit(),
                        "待聘"
                );
                lessonResponseList.add(response);
            }
        }
        return new ListLessonResponse(lessonResponseList);
    }

    public void deleteLesson(LessonRequest lessonRequest){
        Optional<Lesson> lesson = lessonRepository.findByLessonName(lessonRequest.getLesson_name());
        if (lesson.isPresent()){
            lessonRepository.delete(lesson.get());
        }
    }
}
