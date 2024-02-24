import {drawUserInfo} from "./storage/storage";
import {STORE_USER_INFO} from "./mutations-type";

export default {
  // 第一个参数是this.$store.state对象
  // 进入到/默认会执行getters,此时是尚未登录的状态,改state只在mutation中改
  userInfo: state => state.userInfo
}
