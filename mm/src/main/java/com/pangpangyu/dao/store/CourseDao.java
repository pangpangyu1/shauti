package com.pangpangyu.dao.store;

import com.pangpangyu.domain.store.Course;

import java.util.List;

public interface CourseDao {
    int save(Course course);

    int delete(Course course);

    int update(Course course);

    Course findById(String id);

    List<Course> findAll();
}
