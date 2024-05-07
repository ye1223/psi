<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { FwbPagination } from 'flowbite-vue'
import { appJsonPost } from '@/api/request'
import useTable from '@/hooks/useTable'
import Search from '@/components/UI/Search.vue'
import Table from '@/components/UI/TableV3.vue'
import Toast from '@/components/UI/Toast'
import Modal from '@/components/UI/Modal.vue'

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
	purchaseNo: '',
	status: 0
})
loadTable({ ...searchFormData })
const handleSearch = (searchText: string) => {
	searchFormData.purchaseNo = searchText
	loadTable({ ...searchFormData })
}

const detailVisible = ref(false)
const handleCancel = () => {
	detailVisible.value = false
}

const detailData = ref<DetailList[]>([])
const handleStrech = (row: PurchaseOrder) => {
	detailVisible.value = true
	detailData.value = row.detailList
}

// 通过进货
const handleAgree = (row: PurchaseOrder) => {
	appJsonPost({
		url: '/purchase/updateStatus',
		data: { id: row['id'], status: 1 }
	}).then(_ => {
		loadTable({ ...searchFormData })
		Toast.success(`成功拒绝${row.id}订单审核`)
	})
}

// 拒绝进货
const handleReject = (row: PurchaseOrder) => {
	appJsonPost({
		url: '/purchase/updateStatus',
		data: { id: row['id'], status: 2 }
	}).then(_ => {
		loadTable({ ...searchFormData })
		Toast.success(`${row.id}订单成功通过进货审核`)
	})
}

// 分页
const handlePageChange = (newPageNow: number) => {
	searchFormData.pageNow = newPageNow
	loadTable({ ...searchFormData })
}
</script>

<template>
	<section class="mt-3">
		<Search placeholder="按进货号模糊搜索" :handleSearch="handleSearch" />
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
			operation
		>
			<template #default="{ row }">
				<span
					class="ml-4 font-medium text-blue-600 dark:text-blue-500 hover:underline"
					@click="handleStrech(row)"
				>
					展开订单
				</span>
				<span
					class="ml-4 font-medium text-green-600 dark:text-green-500 hover:underline"
					@click="handleAgree(row)"
				>
					通过
				</span>
				<span
					class="ml-4 font-medium text-red-600 dark:text-red-500 hover:underline"
					@click="handleReject(row)"
				>
					拒绝
				</span>
			</template>
		</Table>
	</section>

	<section class="mt-10 text-center" v-if="tableData.total">
		<fwb-pagination
			v-model="searchFormData.pageNow"
			:total-items="tableData.total"
			:perPage="searchFormData.pageSize"
			@page-changed="handlePageChange"
			show-icons
			:showLabels="false"
			large
		/>
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
