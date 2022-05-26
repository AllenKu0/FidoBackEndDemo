package com.example.springboot.response;

import com.example.springboot.entity.Teach;

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
        public int getLesson_index() {
            return lesson_index;
        }

        public String getLesson_name() {
            return lesson_name;
        }

        public Integer getLesson_credit() {
            return lesson_credit;
        }

        public String teachNmae;


        public String getTeachNmae() {
            return teachNmae;
        }

        public void setTeachNmae(String teachNmae) {
            this.teachNmae = teachNmae;
        }

        private int lesson_index=0;
        private String lesson_name="";
        private Integer lesson_credit=0;

        public LessonResponse(int lesson_index,String lesson_name, Integer lesson_credit,String teachNmae) {
            this.lesson_index=lesson_index;
            this.lesson_name = lesson_name;
            this.lesson_credit = lesson_credit;
            this.teachNmae = teachNmae;
        }
    }
}
