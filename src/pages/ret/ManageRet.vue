<script lang="ts" setup>
import { reactive, ref } from 'vue'
import useTable from '@/hooks/useTable'
import Search from '@/components/UI/Search.vue'
import Table from '@/components/UI/TableV3.vue'
import Modal from '@/components/UI/Modal.vue'
import Toast from '@/components/UI/Toast'
import Confirm from '@/components/UI/Confirm/index'
import { formGet } from '@/api/request'
interface RetOrderDetail {
	id: number
	retNo: string
	goodsId: number
	goodsName: string
	unit: string
}

interface RetOrder {
	detailList: RetOrderDetail[]
	id: number
	operatorId: number
	operatorName: string
	purchaseNo: string
	retNo: string
	returnDate: string
	status: number
	statusDesc: string
}

const searchFormData = reactive({
	pageNow: 1,
	pageSize: 10,
	fuzzyRetNo: ''
})
const { loadTable, tableData } = useTable<RetOrder>({
	fetchUrl: '/returnGoods/loadTable'
})

loadTable()

const handleSearch = (searchText: string) => {
	if (!searchText) {
		Toast.error('输入为空')
		return
	}
	searchFormData.fuzzyRetNo = searchText
	loadTable({ ...searchFormData })
}

const detailVisible = ref(false)
const handleCancel = () => {
	detailVisible.value = false
}

// 订单展开详情
const detailData = ref<RetOrderDetail[]>([])
const handelStrech = (row: RetOrder) => {
	detailVisible.value = true
	detailData.value = row.detailList
}
const handleDelete = (row: RetOrder) => {
	Confirm()
		.show()
		.then(() => {
			formGet({
				url: '/returnGoods/deleteById',
				params: { id: row.id }
			})
				.then(_ => {
					loadTable()
				})
				.catch(result => {
					Toast.error(result.errMsg)
				})
		})
		.catch(() => {})
}
</script>

<template>
	<section>
		<div>
			<Search
				placeholder="按退货号进行模糊搜索"
				:handleSearch="handleSearch"
			/>
		</div>
		<div class="mt-8 pr-8">
			<Table
				:list="tableData.rows as RetOrder[]"
				:labels="[
					['id', '系统编号'],
					['purchaseNo', '所属进货编号'],
					['retNo', '退回编号'],
					['operatorName', '经办人'],
					['statusDesc', '状态'],
					['returnDate', '退货日期']
				]"
				:operation="true"
			>
				<template #default="{ row }">
					<span
						class="ml-4 font-medium text-blue-600 dark:text-blue-500 hover:underline"
						@click="handelStrech(row)"
					>
						详情
					</span>
					<span
						class="ml-4 font-medium text-red-600 dark:text-red-500 hover:underline"
						@click="handleDelete(row)"
					>
						删除
					</span>
				</template>
			</Table>
		</div>
	</section>

	<Modal
		title="订单详情"
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
				['returnPrice', '退货价'],
				['num', '数量'],
				['supplierName', '供应商']
			]"
			:operation="false"
		></Table>
	</Modal>
</template>

<style scoped></style>
