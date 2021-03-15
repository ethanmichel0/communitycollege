package com.communitycollege;

import com.communitycollege.Entity.Course;
import com.communitycollege.Entity.Student;
import com.communitycollege.Repository.CourseRepository;
import com.communitycollege.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.ArrayList;
@SpringBootApplication
public class CommunitycollegeApplication implements ApplicationRunner{

	private StudentRepository studentRepository;
	private CourseRepository courseRepository;

	@Autowired
	public CommunitycollegeApplication (StudentRepository studentRepository, CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CommunitycollegeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		List<Course> courses = new ArrayList<Course>();
		List<Student> students = new ArrayList<Student>();

		Course Math = new Course("Math", new java.sql.Time(4,00,00));
		Course English = new Course("English", new java.sql.Time(10,00,00));
		Course Science = new Course("Science", new java.sql.Time(3,00,00));

		courses.add(Math);
		courses.add(Science);
		courses.add(English);

		Student Robert = new Student("Robert", "robert@gmail.com");
		Student Bill = new Student("Bill", "bill@gmail.com");
		Student John = new Student("John", "jon@gmail.com");
		Student Mary = new Student("Mary", "mary@gmail.com");

		students.add(Robert);
		students.add(Bill);
		students.add(John);
		students.add(Mary);

		Robert.addCourse(Math);
		Robert.addCourse(English);

		Bill.addCourse(Math);
		Bill.addCourse(Science);

		John.addCourse(English);
		John.addCourse(Math);

		courseRepository.saveAll(courses);
		studentRepository.saveAll(students);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
}
