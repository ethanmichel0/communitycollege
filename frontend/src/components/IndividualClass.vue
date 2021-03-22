<template>
<div class="card">
    <h3>{{name}}</h3>
    <p> Enrollment Size: {{enrollmentNum}}</p>
    <input v-if="option == 'drop'" type="button" value="Drop Course" @click="$emit('dropCourse')">
    <input v-else type="button" value="Add Course" @click="$emit('addCourse')" :disabled="courseAlreadyAdded">
</div>
</template>

<script>
export default {
  name: 'IndividualClass ',
  props: {
    name: String,
    enrollmentNum: Number,
    option: String,
    studentId: Number,
    courseId: Number,
    courseAlreadyAdded: Boolean,
    addCourse: Function
  },
}
</script>
