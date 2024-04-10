<script lang="ts" setup>
import { computed, ref, toRaw } from 'vue'
import { appJsonPost, formGet } from '@/api/request'
import { now } from '@/utils/date'
import CheckGoods from '@/components/CheckGoods.vue'
import BackButton from '@/components/UI/BackButton.vue'
// import Table from '@/components/UI/Table.vue'
import {
	FwbTable,
	FwbTableBody,
	FwbTableCell,
	FwbTableHead,
	FwbTableHeadCell,
	FwbTableRow
} from 'flowbite-vue'
import { useRouter } from 'vue-router'
import Toast from '@/components/UI/Toast'
// import { GoodsInfo } from '@/ts/interfaces/goods.interface'

interface OrderDetail {
	goodsId: string
	goodsName: string
	unit: string
	salePrice: string
	num: string
	supplierId: string
	supplierName: string
}
interface SaleOrder {
	orderNo: string
	createTime: string
	detailList: OrderDetail[]
}
const form = ref<SaleOrder>({
	orderNo: '',
	createTime: '',
	detailList: []
})

const initSaleOrderNo = async () => {
	const { data } = await formGet<string>({
		url: '/saleOrder/getNo'
	})
	form.value.orderNo = data
}

// 总金额
const totalAmount = computed(() => {
	let total = 0
	if (form.value.detailList) {
		form.value.detailList.forEach(crtValue => {
			total += Number(crtValue.salePrice) * Number(crtValue.num)
		})
	}
	return total
})

const initPurchaseDate = () => {
	form.value.createTime = now()
}

initSaleOrderNo()
initPurchaseDate()

const handleAddRow = () => {
	form.value.detailList.push({
		goodsId: '',
		goodsName: '',
		unit: '',
		salePrice: '',
		num: '',
		supplierId: '',
		supplierName: ''
	})

	fillInput.value.push({
		num: 0
	})
}

// 通过点击表格的第几行打开的选择商品对话框
const forRowIndex = ref(-9999) //第几行数据
const checkGoodsVisible = ref(false)
const openCheckGoods = (index: number) => {
	checkGoodsVisible.value = true
	forRowIndex.value = index
}

const handleSelectedData = (selectedGoods: any) => {
	console.log(selectedGoods)
	let idx = selectedGoods['rowIndex']
	form.value.detailList[idx].goodsId = selectedGoods.id
	form.value.detailList[idx].salePrice = selectedGoods.salePrice
	form.value.detailList[idx].goodsName = selectedGoods.name
	form.value.detailList[idx].unit = selectedGoods['unit']
	// 关闭对话框
	checkGoodsVisible.value = false
}

const handleClose = (status: boolean) => {
	checkGoodsVisible.value = status
}

const router = useRouter()
const handleSubmit = () => {
	form.value.detailList.forEach((detail, index) => {
		Object.assign(detail, fillInput.value[index])
	})
	appJsonPost({
		url: '/saleOrder/add',
		data: { ...toRaw(form.value), totalAmount: totalAmount.value }
	})
		.then(_ => {
			Toast.success('提交销售订单成功')
			router.replace('/saleOrder/manage')
		})
		.catch(err => {
			console.log(err, { ...toRaw(form.value), totalAmount })
			Toast.error(err.errMsg)
		})
}
const fillInput = ref([
	{
		num: 0
	}
])

const handleBack = () => {
	router.back()
}
</script>

<template>
	<!-- <div>addpurchase</div> -->
	<section class="mb-8">
		<BackButton @click="handleBack"></BackButton>
	</section>
	<section>
		<form class="space-y-4 md:space-y-6">
			<div>
				<label
					for="purchaseNo"
					class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>
					销售单号
				</label>
				<input
					type="text"
					id="purchaseNo"
					class="input-disabled"
					disabled
					readonly
					v-model="form.orderNo"
				/>
			</div>
			<div>
				<label
					for="purchaseNo"
					class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>
					总金额
				</label>
				<input
					type="text"
					id="purchaseNo"
					class="input-disabled"
					disabled
					readonly
					v-model="totalAmount"
				/>
			</div>
			<div>
				<label
					for="purchaseNo"
					class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>
					订单创建时间
				</label>
				<input
					type="text"
					id="purchaseNo"
					class="input-disabled"
					disabled
					readonly
					v-model="form.createTime"
				/>
			</div>
		</form>
	</section>
	<section class="mt-8 flex flex-row justify-center">
		<button class="button-normal" @click="handleSubmit">
			提交销售订单
		</button>
		<button class="button-default" @click="handleAddRow">添加一行</button>
	</section>

	<section class="mt-8">
		<fwb-table>
			<fwb-table-head>
				<fwb-table-head-cell>商品名</fwb-table-head-cell>
				<fwb-table-head-cell>单位</fwb-table-head-cell>
				<fwb-table-head-cell>销售价（元）</fwb-table-head-cell>
				<fwb-table-head-cell>销售数量</fwb-table-head-cell>
			</fwb-table-head>
			<fwb-table-body
				v-for="(row, index) in form.detailList"
				:key="index"
			>
				<fwb-table-row>
					<fwb-table-cell>
						<input
							type="text"
							v-model="row.goodsName"
							class="input-disabled"
							@click="openCheckGoods(index)"
						/>
					</fwb-table-cell>
					<fwb-table-cell>
						<input
							type="text"
							v-model="row.unit"
							class="input-disabled"
							disabled
						/>
					</fwb-table-cell>
					<fwb-table-cell>
						<!-- 销售价 -->
						<input
							type="text"
							class="input-disabled"
							v-model="row.salePrice"
							disabled
						/>
					</fwb-table-cell>
					<fwb-table-cell>
						<!-- 销售数量 -->
						<input
							type="number"
							class="input-default"
							min="1"
							v-model="fillInput[index].num"
						/>
					</fwb-table-cell>
				</fwb-table-row>
			</fwb-table-body>
		</fwb-table>
	</section>
	<CheckGoods
		:diaVisible="checkGoodsVisible"
		:forRowIndex="forRowIndex"
		@offerSelectedData="handleSelectedData"
		@closeModal="handleClose"
	/>
</template>

<style scoped></style>
