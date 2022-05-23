package com.example.springboot.response;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class ListLessonResponse {

    public ListLessonResponse(List<LessonResponse> lessonResponseList) {
        this.lessonResponseList = lessonResponseList;
    }

    private List<LessonResponse> lessonResponseList=new ArrayList<>();

    public List<LessonResponse> getLessonResponseList() {
        return lessonResponseList;
    }

    public void setLessonResponseList(List<LessonResponse> lessonResponseList) {
        this.lessonResponseList = lessonResponseList;
    }

    public static class  LessonResponse{
        private int lesson_index=0;
        private String lesson_name="";
        private Integer lesson_credit=0;

        public LessonResponse(int lesson_index,String lesson_name, Integer lesson_credit) {
            this.lesson_index=lesson_index;
            this.lesson_name = lesson_name;
            this.lesson_credit = lesson_credit;
        }
    }
}
