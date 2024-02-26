// select
export interface CategoryInfo {
	id: string
	name: string
}

// 增加表单
export interface AddCategoryForm {
	natureId: string
	name: string
}

// 搜索表单
export interface SearchForm {
    pageNow: number
	pageSize: number
	searchWord: string
}

export interface TableList {
	creatorId?: number
	id: number
	name: string
	natureId: number
	natureName?: string
}
// 表格属性
export interface TableData {
	total: number
	list: TableList[]
}
