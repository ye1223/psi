import {STORE_USER_INFO} from "./mutations-type";
import {storeUserInfo} from "./storage/storage";

export default {
  // 每个方法的第一个入参都是state对象,第二个可以是自定义传递的参数,
  // STORE_USER_INFO:事件类型 ，后边的叫该事件的回调函数
  [STORE_USER_INFO](state,payload) {
    // vuex状态
    state.userInfo = payload;
  }
}
