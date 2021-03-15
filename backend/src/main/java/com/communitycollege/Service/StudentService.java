package com.communitycollege.Service;

import com.communitycollege.DTO.CourseDTO;
import com.communitycollege.DTO.StudentDTO;
import com.communitycollege.Entity.Course;
import com.communitycollege.Entity.Student;
import com.communitycollege.Repository.CourseRepository;
import com.communitycollege.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    StudentRepository repository;
    CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    public static StudentDTO entityToDTO(Student student) {
        ModelMapper map = new ModelMapper();
        StudentDTO studentDTO = map.map(student,StudentDTO.class);
        return studentDTO;
    }

    public StudentDTO addStudent(Student student) {
        return entityToDTO(repository.save(student));
    }

    public List<StudentDTO> getStudents() {
        return repository.findAll().stream().map(StudentService::entityToDTO).
                collect(Collectors.toList());
    }

    public StudentDTO getStudentById(int id) {
        Student student = repository.findById(id).orElse(null);
        return entityToDTO(student);
    }

    public boolean deleteStudent(int id) {
        repository.deleteById(id);
        return true;
    }

    public StudentDTO updateStudent(Student student) {
        Student existingStudent = repository.findById(student.getId()).orElse(null);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        return entityToDTO(repository.save(existingStudent));
    }

    public boolean addStudentToCourse(int student_id, int course_id) {
        Student student = repository.findById(student_id).orElse(null);
        Course course = courseRepository.findById(course_id).orElse(null);
        student.addCourse(course);
        repository.save(student);
        return true;
    }

    public boolean removeStudentFromCourse(int student_id, int course_id) {
        Student student = repository.findById(student_id).orElse(null);
        Course course = courseRepository.findById(course_id).orElse(null);
        student.removeCourse(course);
        repository.save(student);
        return true;
    }

    public Set<CourseDTO> listCoursesEnrolledIn(int id) {
        Student student = repository.findById(id).orElse(null);
        return student.getCourses().stream().map((e) -> CourseService.entityToDTO(e,false)).collect(Collectors.toSet());
    }
}
