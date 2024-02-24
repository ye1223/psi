import Vue from 'vue'
import Vuex from 'vuex'
import actions from "./actions";
import mutations from "./mutations";
import getters from "./getters";

// 安装
Vue.use(Vuex);

const state = {
  userInfo:{
    userId:-1,
    userName:'',
    roleId:-1,
    roleName:''
  }
};
const store = new Vuex.Store({
  // state存储数据
  state,
  actions,
  mutations,
  getters
});

export default store;
