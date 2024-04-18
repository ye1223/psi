export interface Detail {
	goodsId: string
	goodsName: string
	unit: string
	buyPrice: string
	num: string
	supplierId: string
	supplierName: string
}
export interface PurchaseInfo {
	purchaseNo: string
	operatorId: string
	operatorName: string
	purchaseDate: string
	detailList: Detail[]
}
