/**
 * 在vue原型链上添加东西, 方便直接调用
 */
import {formGet,formPost,appJsonPost} from '../network/request'


// 总接口
function bindToVue(vue) {
  bindHttp(vue)
}

// ajax 相关
function bindHttp(vue) {
  vue.prototype.$formGet = formGet
  vue.prototype.$formPost = formPost
  vue.prototype.$appJsonPost = appJsonPost
}

export default bindToVue
