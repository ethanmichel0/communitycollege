<template>
  <form v-if="studentId === null">
    Please enter your email<input type="text" v-model="enteredStudentEmail">
  <input type="submit" value="Log in" @click.prevent="validateEmail">
  <h3 v-if="invalidEmailEntered">Your email is invalid!</h3>
  </form>
  <div v-else> <!-- Navigation at top of screen --> 
    <header>
      <nav>
        <ul>
          <li @click="changeCurrentComponent('CurrentClasses')">
            View Current Registration
          </li>
          <li @click="changeCurrentComponent('AllClasses')">
            View All Courses
          </li>
        </ul>
      </nav>
    </header>
    <component :is="currentComponent" :id=studentId></component>
  </div>
</template>

<script>
import axios from 'axios';
import currentclasses from './components/CurrentClasses.vue';
import allclasses from './components/AllClasses.vue';

export default {
  name: 'App',
  components: {
    'CurrentClasses' : currentclasses,
    'AllClasses' : allclasses
  },
  data () {
    return {
      allStudentInformation: null,
      invalidEmailEntered: null,
      enteredStudentEmail: null,
      studentId: null,
      currentComponent: 'CurrentClasses'
    }
  },

  methods : {
    validateEmail() {
      const allEmails = this.allStudentInformation.map(student => student.email);
      if(allEmails.includes(this.enteredStudentEmail)) { 
        const studentWithEmail = this.allStudentInformation.filter(student => student.email == this.enteredStudentEmail);
        this.studentId = studentWithEmail[0].id;
        this.invalidEmailEntered = false;
      } else {
        this.invalidEmailEntered = true;
      }
    },
    changeCurrentComponent(component) {
      this.currentComponent = component;
    }
  },

  async created () {
    const allStudentsFullRequest = await axios.get("http://localhost:8080/students/all");
    this.allStudentInformation = allStudentsFullRequest.data;
  }

}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

header {
  width: 100%;
  height: 5rem;
  background-color: #11005c;
}

nav {
  height: 100%;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

li {
  margin: 0 2rem;
  text-decoration: none;
  background: transparent;
  border: 1px solid transparent;
  cursor: pointer;
  color: white;
  padding: 0.5rem 1.5rem;
  display: inline-block;
}

li:hover {
  border-color: #771c51;
  background-color: #1a037e;
  border-width: 10px;
}
li.active, li:active {
  color:green
}
.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 40%;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.container {
  padding: 2px 16px;
}
</style>