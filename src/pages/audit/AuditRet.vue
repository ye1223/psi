<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { appJsonPost } from '@/api/request'
import useTable from '@/hooks/useTable'
import Search from '@/components/UI/Search.vue'
import Table from '@/components/UI/TableV3.vue'
import Toast from '@/components/UI/Toast'
import Modal from '@/components/UI/Modal.vue'

interface ReturnDetail {
	id: number
	refundId: number
	goodsId: number
	goodsName: string
	refundPrice: number
	num: number
}

interface ReturnGoods {
	createTime: string
	detailList: ReturnDetail[]
	id: number
	operatorId: number
	operatorName: string
	purchaseNo: string
	retNo: string
	returnDate: string
	status: number
	statusDesc: string
}
const { loadTable, tableData } = useTable<ReturnGoods>({
	fetchUrl: '/returnGoods/loadTable'
})

const searchFormData = reactive({
	pageNow: 1,
	pageSize: 10,
	fuzzyRetNo: '',
	status: 0
})
loadTable({ ...searchFormData })
const handleSearch = (searchText: string) => {
	searchFormData.fuzzyRetNo = searchText
	loadTable({ ...searchFormData })
}

const detailVisible = ref(false)
const handleCancel = () => {
	detailVisible.value = false
}

const detailData = ref<ReturnDetail[]>([])
const handleStrech = (row: ReturnGoods) => {
	detailVisible.value = true
	detailData.value = row.detailList
}

// 通过退货审核
const handleAgree = (row: ReturnGoods) => {
	appJsonPost({
		url: '/returnGoods/updateStatus',
		data: { id: row['id'], status: 1 }
	}).then(_ => {
		loadTable({ ...searchFormData })
		Toast.success(`${row.id}订单成功通过退货审核`)
	})
}

// 拒绝退回审核
const handleReject = (row: ReturnGoods) => {
	appJsonPost({
		url: '/returnGoods/updateStatus',
		data: { id: row['id'], status: 2 }
	}).then(_ => {
		loadTable({ ...searchFormData })
		Toast.success(`成功拒绝${row.id}订单退退货审核`)
	})
}
</script>

<template>
	<section class="mt-3">
		<Search placeholder="按退货号模糊搜索" :handleSearch="handleSearch" />
	</section>
	<section class="mt-8">
		<Table
			:labels="[
				['id', '系统编号'],
				['purchaseNo', '所属进货编号'],
				['retNo', '退货编号'],
				['operatorName', '经办人'],
				['statusDesc', '状态'],
				['returnDate', '退货日期']
			]"
			:list="tableData.rows as ReturnGoods[]"
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
				['returnPrice', '退货价'],
				['num', '数量'],
				['supplierName', '供应商']
			]"
			:operation="false"
		/>
	</Modal>
</template>

<style scoped></style>
