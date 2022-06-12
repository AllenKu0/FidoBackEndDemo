package com.example.springboot.service;

import com.example.springboot.entity.CourseSelection;
import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.User;
import com.example.springboot.repository.CourseSelectionRepository;
import com.example.springboot.repository.LessonRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.LessonRequest;
import com.example.springboot.response.LessonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseSelectionRepository courseSelectionRepository;

    @Autowired
    UserRepository userRepository;

    public void saveLesson(LessonRequest lessonRequest) {
        Lesson lesson = new Lesson(lessonRequest.getLesson_name()
                , lessonRequest.getLesson_credit(), lessonRequest.getLesson_status());
        lessonRepository.save(lesson);
    }

    public List<LessonResponse> getNotSelectLesson(String account) {
        List<LessonResponse> lessonResponseList = new ArrayList<>();
        Optional<User> user = userRepository.findByAccount(account);
        Optional<List<CourseSelection>> courseSelections = courseSelectionRepository.findCourseSelectionByUser(user.get());
        int lessonSize = lessonRepository.findAll().size();
        for (int i = 0; i < lessonSize; i++) {
            if (courseSelections.isPresent()) {
                int courseSelectSize = courseSelections.get().size();
                for (int j = 0; j < courseSelectSize; j++) {
                    if (!courseSelections.get().get(j).getLesson().getLessonName()
                            .equals(lessonRepository.findAll().get(i).getLessonName())) {
                        Lesson lesson = lessonRepository.findAll().get(i);
                        LessonResponse response = new LessonResponse(
                                lesson.getLessonId().intValue(),
                                lesson.getLessonName(),
                                lesson.getLessonCredit(),
                                lesson.getLessonStatus()
                        );
                        lessonResponseList.add(response);
                    }
                }
            }
        }
        return lessonResponseList;
    }

    public List<LessonResponse> getAllLesson() {
        List<LessonResponse> lessonResponseList = new ArrayList<>();
        int lessonSize = lessonRepository.findAll().size();
        for (int i = 0; i < lessonSize; i++) {
            Lesson lesson = lessonRepository.findAll().get(i);
            LessonResponse response = new LessonResponse(
                    lesson.getLessonId().intValue(),
                    lesson.getLessonName(),
                    lesson.getLessonCredit(),
                    lesson.getLessonStatus()
            );
            lessonResponseList.add(response);
        }
        return lessonResponseList;
    }

    public void deleteLesson(String lessonName) {
        Optional<Lesson> lesson = lessonRepository.findByLessonName(lessonName);
        if (lesson.isPresent()) {
            lessonRepository.delete(lesson.get());
        }
    }
}
