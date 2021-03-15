import { createApp } from 'vue'
import App from './App.vue'
import individualclass from './components/IndividualClass.vue'

const app = createApp(App);

app.component('individual-class',individualclass);

app.mount('#app')
