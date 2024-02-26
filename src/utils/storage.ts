//@ts-nocheck
import { TOKEN_KEY, USER_INFO_KEY } from "@/utils/constants";

export function drawToken() {
  return window.sessionStorage.getItem(TOKEN_KEY)
}

/**
 * layout.vue中调用获取用户对象
 */
export function drawUserInfo() {
  return JSON.parse(window.sessionStorage.getItem(USER_INFO_KEY));
}
/**
 * 登录成功后调用
 */
export function storeUserInfo(userObj) {
  window.sessionStorage.setItem(USER_INFO_KEY, JSON.stringify(userObj));
}

/**
 * 登录成功后调用
 */
export function storeToken(token) {
  window.sessionStorage.setItem(TOKEN_KEY, token)
}

/**
 * 注销退出时调用
 */
export function clearStorage() {
  // window.sessionStorage.removeItem(TOKEN_KEY);
  // window.sessionStorage.removeItem(USER_INFO_KEY);
  window.sessionStorage.clear()
}
