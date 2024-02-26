import { Attributes } from "./request.interface"

export interface BillInfo {
	billTime: string
	categoryId: string
	amountMoney: string
	remarks: string
}

export interface BillTableInfo {
	creatorId: string
	id: number
	name: string
	natureId: string
	natureName: string
}

export interface BillTableData extends Attributes {
    total: number
    list: BillTableInfo[]
}
