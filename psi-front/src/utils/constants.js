export const BASE_URL = process.env.VUE_APP_BASE_URL
export const TOKEN_KEY = 'user_token'
export const USER_INFO_KEY = 'user_info'
export const IGNORE_URLS = ['/'];
// 角色---权限通配
export const ROLE_AUTH = {
  1:["*"],
  2:['/index','/purchase',"/returnGoods"],
  3:['/index','/saleOrder',"/refund"],
  4:['/index','/audit'],
}
