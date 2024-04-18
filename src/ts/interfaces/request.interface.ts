// import { LoginForm } from './login.interface'
// import { UserInfo } from './userinfo.interface'

interface TypeId  {
	id?: string | number
	userId?: string
}
// 参数
export interface ApiFormGetParams {
	url: string
	params?: TypeId
}

export interface ApiFormPostParams<P> {
	url: string
	data: P
}

export interface ApiAppJsonPostParams<P> {
	url: string
	data: P
}

// 账单管理的属性 收入-支出-区间结余
export interface Attributes {
	sectionRemain: number
	totalIn: number
	totalOut: number
}

// 返回结果
export interface ApiResult<T> {
	attributes: Attributes
	// code: number
	data: T
	failed: boolean
	success: boolean
	// msg: string
	total: any
	errMsg: string
	errCode: number
}


// ApiResult data 返回结果
// export interface LoginResult extends UserInfo {}
export interface LoginResult {
	token: string
}
