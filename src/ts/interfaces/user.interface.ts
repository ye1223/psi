import { UserInfo } from './userinfo.interface'

export interface SearchForm {
	pageNow: number
	pageSize: number
	userName: string
}


export type UserRole = Pick<UserInfo, 'roleId' | 'roleName'>

export interface UserAddForm {
	userName: string
	pwd: string
	roleId: number
}
