package com.communitycollege.Controller;

import com.communitycollege.DTO.CourseDTO;
import com.communitycollege.Entity.Course;
import com.communitycollege.Entity.Student;
import com.communitycollege.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseResource {
    private CourseService courseService;
    @Autowired
    public CourseResource (CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public List<CourseDTO> getAllCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }

    @PutMapping("")
    public CourseDTO updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course);
    }

    @PostMapping("")
    public CourseDTO addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }
}
