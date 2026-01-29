package com.springboot.catalog.domain.event;

public class CourseEvent {

    private Long courseId;
    private String title;
    private String action;

    public CourseEvent() {
    }

    public CourseEvent(Long courseId, String title, String action) {
        this.courseId = courseId;
        this.title = title;
        this.action = action;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
