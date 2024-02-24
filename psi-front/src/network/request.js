import axios from 'axios'
import {drawToken} from "../store/storage/storage";
import {BASE_URL} from "../utils/constants";

// 配置拦截器
function addInterceptors(req) {

  req.interceptors.request.use(config => {
    // 让每次请求都携带Authorization请求头
    config.headers['Authorization'] = drawToken()
    if (config.headers["Content-Type"].toLowerCase().indexOf("application/json") > -1) {
      if (!config.data) {
        // 后端如果是@RequestBody接受,data又没有的话后端会报错
        config.data = JSON.stringify({"_": new Date().getTime()})
      }
    } else {
      if (!config.params) {
        // 默认传个时间戳
        config.params = {};
        config.params['_'] = new Date().getTime()
      }
    }
    return config; // 一定要返回
  }, error => {
    console.log("请求发送失败", error);
  });
  // 2.响应拦截器
  req.interceptors.response.use(result => {
    // 直接返回服务端返回的结果
    let resultVO = result.data;
    if (resultVO.success) {
      return resultVO;
    } else {
      console.log("业务异常:", resultVO.errMsg);
      // 直接自己返回Promise
      return Promise.reject(resultVO);
    }
  }, error => {
    console.log("服务器错误", error);
    return Promise.reject("服务器错误");
  });
}

let baseCfg = {
  baseURL: BASE_URL,
  timeout: 1000 * 60 * 10,//单位ms
  headers: {
    "Content-Type": "application/x-www-form-urlencoded"
  }
};
/*
 axios.create返回值:AxiosInstance对象实例
 */
let formGetCfg = {"method": "get"};// 默认就是get
Object.assign(formGetCfg, baseCfg);
let formGetInstance = axios.create(formGetCfg);
addInterceptors(formGetInstance);


let formPostCfg = {"method": "post"};
Object.assign(formPostCfg, baseCfg);
let formPostInstance = axios.create(formPostCfg);
addInterceptors(formPostInstance);


let appJsonPostCfg = Object.assign({}, baseCfg,{
  "method": "post",
  headers: {
    "Content-Type": "application/json"
  }
});
let appJsonPostInstance = axios.create(appJsonPostCfg);
addInterceptors(appJsonPostInstance);

export function formGet(config) {
  // 返回AxiosPromise对象,外部可以直接使用.then或.catch
  return formGetInstance(config);
}

export function formPost(config) {
  return formPostInstance(config);
}
// appjson时不用get方式
export function appJsonPost(config) {
  return appJsonPostInstance(config);
}
