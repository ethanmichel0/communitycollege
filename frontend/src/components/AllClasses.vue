<template>
    <div v-for="course in allCourses" :key="course.id">
        <individual-class
            :name="course.name"
            :enrollmentNum="course.enrollmentNum"
            option="add"
            :studentId="id"
            :courseId="course.id"
            :courseAlreadyAddedInitially="studentInCourse(course.id)"
            @courseDropped="dropCourse(course.id)">
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
            allCourses: null,
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
            if(this.currentCourses === null) {
                return true; // this is a bug
            }
            const alreadyInCoures = this.currentCourses.filter(course => course.id == courseId).length !== 0;
            return alreadyInCoures;
        }
    }
}
</script>