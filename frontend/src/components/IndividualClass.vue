<template>
<div class="card">
    <h3>{{name}}</h3>
    <p> Enrollment Size: {{enrollmentNumInitially}}</p>
    <input v-if="option == 'drop'" type="button" value="Drop Course" @click="dropCourse(courseId)">
    <input v-else type="button" value="Add Course" @click="addCourse(courseId)" :disabled="courseAlreadyAdded">
</div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'IndividualClass ',
  props: {
    name: String,
    enrollmentNumInitially: Number,
    option: String,
    studentId: Number,
    courseId: Number,
    courseAlreadyAddedInitially: Boolean
  },
  data () {
      return {
          courseAlreadyAdded : this.courseAlreadyAddedInitially,
          enrollmentNum : this.enrollmentNumInitially
      }
  },
  created() {
  },
  methods : {
      async dropCourse (courseId) {
          if(confirm("Are you sure you want to drop this course?")) {
              await axios.delete(`http://localhost:8080/students/${this.studentId}/courses/${courseId}`);
              this.$emit('courseDropped');
          }
      },
      async addCourse (courseId) {
          const addCourseRequest = await axios.post(`http://localhost:8080/students/${this.studentId}/courses/${courseId}`);
          if(addCourseRequest.status == 200) {
              this.courseAlreadyAdded = true;
              this.enrollmentNum ++;
          }
      }
  }
}
</script>
