<script lang="ts" setup>
import { reactive, ref } from 'vue'
import useTable from '@/hooks/useTable'
import Search from '@/components/UI/Search.vue'
import Table from '@/components/UI/TableV3.vue'
import Modal from '@/components/UI/Modal.vue'

interface RefundDetail {
	id: number
	refundId: number
	goodsId: number
	goodsName: string
	refundPrice: number
	num: number
}

interface RefundOrder {
	createTime: string
	detailList: RefundDetail[]
	id: number
	orderNo: string
	refundNo: string
	totalAmount: number
}

const { loadTable, tableData } = useTable<RefundOrder>({
	fetchUrl: '/refund/loadTable'
})

const searchFormData = reactive({
	pageNow: 1,
	pageSize: 10,
	fuzzyRefundNo: '',
	status: 0
})
loadTable({ ...searchFormData })
const handleSearch = async (searchText: string) => {
	searchFormData.fuzzyRefundNo = searchText
	// Object.assign(searchFormData, { fuzzyRefundNo: searchText })
	await loadTable({ ...searchFormData })
}

const detailVisible = ref(false)
const handleCancel = () => {
	detailVisible.value = false
}

const detailData = ref<RefundDetail[]>([])
const handleStrech = (row: RefundOrder) => {
	detailVisible.value = true
	detailData.value = row.detailList
}
</script>

<template>
	<section class="mt-3">
		<Search placeholder="按退款号模糊搜索" :handleSearch="handleSearch" />
	</section>
	<section class="mt-8">
		<Table
			:labels="[
				['id', '系统编号'],
				['orderNo', '所属订单号'],
				['refundNo', '退款编号'],
				['totalAmount', '总金额'],
				['createTime', '退款日期']
			]"
			:list="tableData.rows as RefundOrder[]"
			operation
		>
			<template #default="{ row }">
				<span
					class="ml-4 font-medium text-blue-600 dark:text-blue-500 hover:underline"
					@click="handleStrech(row)"
				>
					展开订单
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
				['refundPrice', '退款价'],
				['num', '数量']
			]"
			:operation="false"
		/>
	</Modal>
</template>

<style scoped></style>
