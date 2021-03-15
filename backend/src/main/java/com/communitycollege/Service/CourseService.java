package com.communitycollege.Service;

import com.communitycollege.DTO.CourseDTO;
import com.communitycollege.Entity.Student;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.communitycollege.Entity.Course;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.communitycollege.Repository.CourseRepository;
import org.modelmapper.ModelMapper;

@Service
public class CourseService {
    CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public static CourseDTO entityToDTO(Course course, boolean exposeEnrollmentNames) {
        ModelMapper mapper = new ModelMapper();
        int enrollmentNum = course.getStudents().size();
        Set<String> enrollment = course.getStudents().stream().map(Student::toString)
                .collect(Collectors.toSet());

        CourseDTO courseDTO = new CourseDTO();

        mapper.map(course, courseDTO);
        courseDTO.setEnrollmentNum(enrollment.size());

        if(exposeEnrollmentNames) {
            courseDTO.setEnrollment(enrollment); // ideally there would be a better system to avoid exposing too much to the students
        }
        return courseDTO;
    }
    public CourseDTO addCourse(Course course) {
        return entityToDTO(repository.save(course),true);
    }

    public List<CourseDTO> getCourses() {
        return repository.findAll().stream().map(e -> entityToDTO(e,true)).collect(Collectors.toList());
    }

    public CourseDTO getCourseById(int id) {
        Course courseEntity = repository.findById(id).orElse(null);
        return entityToDTO(courseEntity, true);
    }

    public boolean deleteCourse(int id) {
        repository.deleteById(id);
        return true;
    }

    public CourseDTO updateCourse(Course course) {
        Course existingCourse = repository.findById(course.getId()).orElse(null);
        existingCourse.setName(course.getName());
        existingCourse.setTime(course.getTime());
        return entityToDTO(repository.save(course),true);
    }
}
