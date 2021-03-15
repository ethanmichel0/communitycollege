package com.communitycollege.Controller;

import com.communitycollege.DTO.CourseDTO;
import com.communitycollege.DTO.StudentDTO;
import com.communitycollege.Entity.Course;
import com.communitycollege.Entity.Student;
import com.communitycollege.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("students")
public class StudentResource {
    private StudentService studentService;
    @Autowired
    public StudentResource (StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<StudentDTO> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudents(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("")
    public StudentDTO updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @PostMapping("")
    public StudentDTO addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}/courses")
    public Set<CourseDTO> listCoursesEnrolledIn(@PathVariable int id) {
        return studentService.listCoursesEnrolledIn(id);
    }

    @PostMapping("/{student_id}/courses/{course_id}")
    public boolean addCourseForStudent(@PathVariable int student_id, @PathVariable int course_id) {
        return studentService.addStudentToCourse(student_id, course_id);
    }

    @DeleteMapping("/{student_id}/courses/{course_id}")
    public boolean removeCourseForStudent(@PathVariable int student_id, @PathVariable int course_id) {
        return studentService.removeStudentFromCourse(student_id, course_id);
    }
}
