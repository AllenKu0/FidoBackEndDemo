package com.example.springboot.service;

import com.example.springboot.entity.CourseSelection;
import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.User;
import com.example.springboot.repository.CourseSelectionRepository;
import com.example.springboot.request.CourseSelectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseSelectionService {
    @Autowired
    private CourseSelectionRepository courseSelectionRespority;

    public void saveCourse(CourseSelectionRequest courseSelectionRequest){
       CourseSelection courseSelection=new CourseSelection(new Lesson(courseSelectionRequest.getLesson_id()),
               new User(courseSelectionRequest.getUser_id()));
        courseSelectionRespority.save(courseSelection);

    }
//    public ListLessonResponse getAllLesson(){
//
//        List< ListLessonResponse.LessonResponse> lessonResponseList=new ArrayList<>();
//        for (int i=0;i<lessonRepository.findAll().size();i++){
//            Lesson lesson= lessonRepository.findAll().get(i);
//            ListLessonResponse.LessonResponse response=new ListLessonResponse.LessonResponse(
//                    i,
//                    lesson.getLesson_name(),
//                    lesson.getLesson_credit()
//            );
//            lessonResponseList.add(response);
//        }
//        return new ListLessonResponse(lessonResponseList);
//    }
}
