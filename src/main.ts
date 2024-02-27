import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from '@/router/index'
import pinia from '@/store/index'

createApp(App).use(pinia).use(router).mount('#app')
