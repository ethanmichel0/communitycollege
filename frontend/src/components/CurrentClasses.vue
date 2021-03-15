<template>
<div v-for="course in courses" :key="course.id">
  <individual-class
    :name="course.name"
    :enrollmentNum="course.enrollmentNum"
    option="drop"
    :studentId="id"
    :courseId="course.id"
    :courseAlreadyAddedInitially="true"
    @courseDropped="dropCourse(course.id)">
  </individual-class>
</div>
<h3 v-if="courses !== null && courses.length === 0">
    Your registration is empty! Register for some more classes.
</h3>
</template>

<script>
import axios from 'axios';
export default {
  name: 'CurrentClasses',
  props: {
    id: Number,
  },
  data() {
      return {
          courses : null
      }
  },
  async created () {
    const studentsRequest = await axios.get(`http://localhost:8080/students/${this.id}/courses`);
    this.courses = studentsRequest.data;
  },
  methods : {
      dropCourse (courseId) {
          this.courses = this.courses.filter((course) => course.id != courseId);
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
