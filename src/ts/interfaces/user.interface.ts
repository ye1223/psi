import { UserInfo } from './userinfo.interface'

export interface SearchForm {
	pageNow: number
	pageSize: number
	userName?: string
	name?: string
	fuzzyRetNo?: string
	status?: number
}


export type UserRole = Pick<UserInfo, 'roleId' | 'roleName'>

export interface UserAddForm {
	userName: string
	pwd: string
	roleId: number
}
