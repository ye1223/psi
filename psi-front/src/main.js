import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import bindToVue from "./init/bindToVue";
import store from "./store";

Vue.config.productionTip = false

Vue.use(ElementUI)
bindToVue(Vue);
new Vue({
  render: h => h(App),
  router,
  store // 本质；Vue.prototype.$store = store;
}).$mount('#app')
