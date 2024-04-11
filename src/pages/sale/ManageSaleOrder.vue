<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { appJsonPost } from '@/api/request'
import useTable from '@/hooks/useTable'
import Search from '@/components/UI/Search.vue'
import Table from '@/components/UI/TableV3.vue'
import Toast from '@/components/UI/Toast'
import Modal from '@/components/UI/Modal.vue'

const router = useRouter()
const handleAdd = () => {
	router.replace('/saleOrder/add')
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
interface SaleOrder {
	id: number
	orderNo: string
	status: number
	statusDesc: string
	detailList: DetailList[]
}
const { loadTable, tableData } = useTable<SaleOrder>({
	fetchUrl: '/saleOrder/loadTable'
})

const searchFormData = reactive({
	pageNow: 1,
	pageSize: 10,
	fuzzyOrderNo: ''
})
loadTable({ ...searchFormData })
const handleSearch = (searchText: string) => {
	searchFormData.fuzzyOrderNo = searchText
	loadTable({ ...searchFormData })
}

const detailVisible = ref(false)
const handleCancel = () => {
	detailVisible.value = false
}

const detailData = ref<DetailList[]>([])
const handelStrech = (row: SaleOrder) => {
	detailVisible.value = true
	detailData.value = row.detailList
}

const selectedRowId = ref<number[]>([])
const handleOfferSelectedData = (selectedId: number[]) => {
	selectedRowId.value = selectedId
}

// 全额退款
const handleRefund = () => {
	const orderNoList: string[] = []
	console.log(selectedRowId.value)
	// 匹配选中的id
	tableData.rows.forEach((row: SaleOrder) => {
		console.log(row)
		// 检查这一行的id是否被选中
		if (selectedRowId.value.includes(row.id)) {
			// 如果被选中，将这一行的进货单号添加到列表中
			orderNoList.push(row.orderNo)
		}
	})

	if (orderNoList.length === 0) {
		Toast.error('请先选择销售单')
		return
	}

	console.log('orderlisttttt', orderNoList)
	appJsonPost({
		url: '/refund/add',
		data: { orderNoList }
	})
		.then(result => {
			loadTable()
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
			<button type="button" class="button-default" @click="handleRefund">
				全部退货
			</button>
		</div>
	</section>
	<section class="mt-8">
		<Table
			:labels="[
				['id', '系统编号'],
				['orderNo', '订单号'],
				['totalAmount', '总金额'],
				['statusDesc', '状态'],
				['createTime', '订单创建日期']
			]"
			:list="tableData.rows as SaleOrder[]"
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
				['salePrice', '销售价'],
				['num', '数量']
			]"
			:operation="false"
		/>
	</Modal>
</template>

<style scoped></style>
