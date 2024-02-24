import {STORE_USER_INFO} from "./mutations-type";
import {formGet} from "../network/request";

export default {
  // context参数相当于$store对象实例
  storeUserInfo(context,payload){
    formGet({
      url: '/user/getUserByToken'
    }).then(result => {
      // 在actions的异步完成后调用mutations的方法
      context.commit(STORE_USER_INFO,result.data);
    });
  }
}
