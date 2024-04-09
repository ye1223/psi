<script lang="ts" setup>
import { useRouter } from 'vue-router'
import Search from '@/components/UI/Search.vue'
import useTable from '@/hooks/useTable'
import { reactive, ref } from 'vue'
import Table from '@/components/UI/TableV3.vue'
import { appJsonPost, formGet } from '@/api/request'
import Toast from '@/components/UI/Toast'
import Modal from '@/components/UI/Modal.vue'

const router = useRouter()
const handleAdd = () => {
	router.replace('/purchase/add')
}
type DetailList = {
	buyPrice: number
	goodsId: number
	goodsName: string
	id: number
	num: number
	purchaseNo: string
	supplierId: number
	supplierName: string
	unit: string
}
interface PurchaseOrder {
	id: number
	operatorId: number
	operatorName: string
	purchaseDate: string
	purchaseNo: string
	status: number
	statusDesc: string
	detailList: DetailList[]
}
const { loadTable, tableData } = useTable<PurchaseOrder>({
	fetchUrl: '/purchase/loadTable'
})

const searchFormData = reactive({
	pageNow: 1,
	pageSize: 10,
	purchaseNo: ''
})
loadTable({ ...searchFormData })
const handleSearch = () => {
	loadTable({ ...searchFormData })
}

const handleDelete = (row: PurchaseOrder) => {
	formGet({
		url: '/purchase/deleteById',
		params: { id: row.id }
	})
		.then(_ => {
			loadTable()
		})
		.catch(result => {
			Toast.error(result.errMsg)
		})
}

const detailVisible = ref(false)
const handleCancel = () => {
	detailVisible.value = false
}

const detailData = ref<DetailList[]>([])
const handelStrech = (row: PurchaseOrder[]) => {
	detailVisible.value = true
	// @ts-ignore
	detailData.value = row.detailList
}

const selectedRowId = ref<number[]>([])
const handleOfferSelectedData = (selectedId: number[]) => {
	selectedRowId.value = selectedId
}

// 全部退货
const handleRetGoods = () => {
	const purchaseNoList: string[] = []

	// 匹配选中的id
	tableData.rows.forEach((row: PurchaseOrder) => {
		// 检查这一行的id是否被选中
		if (selectedRowId.value.includes(row.id)) {
			// 如果被选中，将这一行的进货单号添加到列表中
			purchaseNoList.push(row.purchaseNo)
		}
	})
	console.log(purchaseNoList)
	if (purchaseNoList.length === 0) {
		Toast.error('请先选择进货单')
		return
	}
	appJsonPost({
		url: '/returnGoods/add',
		data: { purchaseNoList }
	})
		.then(result => {
			Toast.success(`${result.data}`)
		})
		.catch(result => {
			Toast.error(`${result.errMsg}`)
		})
}
</script>

<template>
	<section class="flex flex-row items-center mt-3 justify-center">
		<div class="text-center my-auto">
			<button type="button" class="button-default" @click="handleAdd">
				✚ 新增
			</button>
		</div>
		<Search placeholder="按进货号模糊搜索" :handleSearch="handleSearch" />
		<div class="">
			<button
				type="button"
				class="button-default"
				@click="handleRetGoods"
			>
				全部退货
			</button>
		</div>
	</section>
	<section class="mt-8">
		<Table
			:labels="[
				['id', '系统编号'],
				['purchaseNo', '进货编号'],
				['operatorName', '经办人'],
				['statusDesc', '状态'],
				['purchaseDate', '进货日期']
			]"
			:list="tableData.rows as PurchaseOrder[]"
			checkbox
			:singleSelectMode="false"
			operation
			:onOfferSelectedData="handleOfferSelectedData"
		>
			<template #default="{ row }">
				<span
					class="ml-4 font-medium text-blue-600 dark:text-blue-500 hover:underline"
					@click="handelStrech(row)"
				>
					展开
				</span>
				<span
					class="ml-4 font-medium text-red-600 dark:text-red-500 hover:underline"
					@click="handleDelete(row)"
				>
					删除
				</span>
			</template>
		</Table>
	</section>

	<Modal
		title="详细"
		:visible="detailVisible"
		:cancel="handleCancel"
		:buttons="[]"
	>
		<Table
			:list="detailData"
			:labels="[
				['id', '系统编号'],
				['goodsName', '商品名'],
				['unit', '单位'],
				['buyPrice', '采购价'],
				['num', '数量'],
				['supplierName', '供应商']
			]"
			:operation="false"
		/>
	</Modal>
</template>

<style scoped></style>
