export const BASE_URL = import.meta.env.VITE_API_BASE_URL
export const TOKEN_KEY = 'user_token'
export const USER_INFO_KEY = 'user_info'
export const IGNORE_URLS = ['/']
// 角色---权限通配
export const ROLE_AUTH = {
	1: ['*'],
	2: ['/index', '/purchase', '/returnGoods'],
	3: ['/index', '/saleOrder', '/refund'],
	4: ['/index', '/audit']
}

export const MENUITEM = {
	BASIC_INFO: 'basicinfo',
	RET: 'ret',
	PURCHASE_REFUND: 'purchase_refund',
	AUDIT: 'audit',
	STATISTIC: 'statistic'
}
