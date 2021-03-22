<template>
    <div v-for="course in allCourses" :key="course.id">
        <individual-class
            :name="course.name"
            :enrollmentNum="course.enrollmentNum"
            option="add"
            :studentId="id"
            :courseId="course.id"
            :courseAlreadyAdded="studentInCourse(course.id)"
            @addCourse="addCourse(course.id)">
        </individual-class>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: "All Classes",
    props: {
        id: Number
    },
    data() {
        return {
            allCourses: [],
            currentCourses: []
        }
    },
    async created() {
        const allCoursesRequest = await axios.get("http://localhost:8080/courses/all");
        this.allCourses = allCoursesRequest.data;
        const currentCoursesRequest = await axios.get(`http://localhost:8080/students/${this.id}/courses`);
        this.currentCourses = currentCoursesRequest.data;
    },
    methods : {
        studentInCourse(courseId) {
            const alreadyInCourse = this.currentCourses.filter(course => course.id == courseId).length !== 0;
            return alreadyInCourse;
        },
        async addCourse(courseId) {
            const addCourseRequest = await axios.post(`http://localhost:8080/students/${this.id}/courses/${courseId}`);
            if(addCourseRequest.status == 200) {
                const course = this.allCourses.filter(course => course.id == courseId)[0];
                course.enrollmentNum ++;
                this.currentCourses.push(course);
            }
        },
    }
}
</script>