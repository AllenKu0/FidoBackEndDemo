package com.example.springboot.service;

import com.example.springboot.entity.CourseSelection;
import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.User;
import com.example.springboot.repository.CourseSelectionRepository;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.CourseSelectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseSelectionService {
    @Autowired
    private CourseSelectionRepository courseSelectionRespority;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveCourse(CourseSelectionRequest courseSelectionRequest) throws Exception {

        Optional<User> user=userRepository.findById(courseSelectionRequest.getUser_id());
        Optional<Lesson> lesson=lessonRepository.findById(courseSelectionRequest.getLesson_id());
        if (lesson.isPresent() && user.isPresent()){
            CourseSelection courseSelection=new CourseSelection(lesson.get(),
                    user.get());
            courseSelectionRespority.save(courseSelection);
        }
        else {
            throw new Exception("no find");
        }


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
