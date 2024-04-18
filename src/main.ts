import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from '@/router/index'
import pinia from './store'
import '../node_modules/flowbite-vue/dist/index.css'

createApp(App).use(pinia).use(router).mount('#app')
