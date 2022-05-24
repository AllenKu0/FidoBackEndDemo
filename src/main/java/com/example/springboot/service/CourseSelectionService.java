package com.example.springboot.service;

import com.example.springboot.entity.CourseSelection;
import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.User;
import com.example.springboot.repository.CourseSelectionRepository;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.CourseSelectionUserIdRequest;
import com.example.springboot.request.CourseSelectionGetAllRequest;
import com.example.springboot.response.CourseSelectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseSelectionService {
    @Autowired
    private CourseSelectionRepository courseSelectionRespority;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveCourse(com.example.springboot.request.CourseSelectionRequest courseSelectionRequest) throws Exception {

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
    public void deleteCourse(com.example.springboot.request.CourseSelectionRequest courseSelectionRequest) throws Exception{
        Optional<User> user=userRepository.findById(courseSelectionRequest.getUser_id());
        Optional<Lesson> lesson=lessonRepository.findById(courseSelectionRequest.getLesson_id());
        if (lesson.isPresent() && user.isPresent()){
            Optional<CourseSelection> courseSelection=courseSelectionRespority.findCourseSelectionByUserAndLesson(user.get(),lesson.get());
            courseSelection.ifPresent(selection -> courseSelectionRespority.delete(selection));
        }
        else {
            throw new Exception("no find");
        }
    }
    public List<CourseSelectionResponse> getAllLesson(CourseSelectionUserIdRequest courseSelectionUserIdRequest){
        List<CourseSelectionResponse> courseSelectionResponses =new ArrayList<>();
        Optional<User> user=userRepository.findById(courseSelectionUserIdRequest.getUser_id());
        if(user.isPresent()){
            if(courseSelectionRespority.findCourseSelectionByUser(user.get()).isPresent()){
                for (int i=0;i<courseSelectionRespority.findCourseSelectionByUser(user.get()).get().size();i++){
                    CourseSelection courseSelection= courseSelectionRespority.findCourseSelectionByUser(user.get()).get().get(i);
                    courseSelectionResponses.add(new CourseSelectionResponse(courseSelection.getLesson().getLessonId(),courseSelection.getLesson().getLessonName()));
                }
            }
        }
        return courseSelectionResponses;
    }
}
