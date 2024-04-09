<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { PurchaseInfo } from '@/ts/interfaces/purchase.interface'
import { appJsonPost, formGet } from '@/api/request'
import useUserInfoStore from '@/store/userInfoStore'
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
const form = ref<PurchaseInfo>({
	purchaseNo: '',
	operatorId: '',
	operatorName: '',
	purchaseDate: '',
	detailList: []
})

// 获取供应商列表
const supplierList = reactive<
	{
		id: string
		name: string
	}[]
>([])
const initSupplierList = async () => {
	const { data } = await formGet({
		url: '/supplier/list'
	})
	Object.assign(supplierList, data)
}
const initPurchaseNo = async () => {
	const { data } = await formGet<string>({
		url: '/purchase/getNo'
	})
	form.value.purchaseNo = data
}
const { userInfo } = useUserInfoStore()
const initUserInfo = () => {
	form.value.operatorId = userInfo.id
	form.value.operatorName = userInfo.userName
}
const initPurchaseDate = () => {
	form.value.purchaseDate = now()
}

initSupplierList()
initPurchaseNo()
initUserInfo()
initPurchaseDate()

const handleAddRow = () => {
	form.value.detailList.push({
		goodsId: '',
		goodsName: '',
		unit: '',
		buyPrice: '',
		num: '',
		supplierId: '',
		supplierName: ''
	})

	fillInput.value.push({
		buyPrice: '',
		num: 0,
		supplierId: -999
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
	let idx = selectedGoods['rowIndex']
	/* const newDetailList = [
		{
			goodsId: '',
			goodsName: '',
			unit: ''
		}
	]
	newDetailList[idx].goodsId = selectedGoods['goodsId']
	newDetailList[idx].goodsName = selectedGoods['name']
	newDetailList[idx].unit = selectedGoods['unit'] */
	form.value.detailList[idx].goodsId = selectedGoods.id
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
	form.value.detailList.forEach((detail, index) =>
		Object.assign(detail, fillInput.value[index])
	)
	console.log('表单数据', { ...form.value })
	appJsonPost({ url: '/purchase/add', data: form.value })
		.then(_ => {
			Toast.success('提交订单成功')
			router.replace('/purchase/manage')
		})
		.catch(err => {
			Toast.error(err.errMsg)
		})
}
const fillInput = ref([
	{
		buyPrice: '',
		num: 0,
		supplierId: -999
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
					进货单号
				</label>
				<input
					type="text"
					id="purchaseNo"
					class="input-disabled"
					disabled
					readonly
					v-model="form.purchaseNo"
				/>
			</div>
			<div>
				<label
					for="purchaseNo"
					class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>
					经办人
				</label>
				<input
					type="text"
					id="purchaseNo"
					class="input-disabled"
					disabled
					readonly
					v-model="form.operatorName"
				/>
			</div>
			<div>
				<label
					for="purchaseNo"
					class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>
					采购日期
				</label>
				<input
					type="text"
					id="purchaseNo"
					class="input-disabled"
					disabled
					readonly
					v-model="form.purchaseDate"
				/>
			</div>
		</form>
	</section>
	<section class="mt-8 flex flex-row justify-center">
		<button class="button-normal" @click="handleSubmit">提交订单</button>
		<button class="button-default" @click="handleAddRow">添加一行</button>
	</section>

	<section class="mt-8">
		<!-- <Table
			:labels="['商品名', '单位', '销售价（元）', '采购数量', '供应商']"
			:list="
				form.detailList.map(({ goodsId, supplierId, ...rest }) => rest)
			"
			:no="false"
		></Table> -->

		<fwb-table>
			<fwb-table-head>
				<fwb-table-head-cell>商品名</fwb-table-head-cell>
				<fwb-table-head-cell>单位</fwb-table-head-cell>
				<fwb-table-head-cell>销售价（元）</fwb-table-head-cell>
				<fwb-table-head-cell>采购数量</fwb-table-head-cell>
				<fwb-table-head-cell>供应商</fwb-table-head-cell>
			</fwb-table-head>
			<!-- v-for="(row, index) in form.detailList.map( ({ goodsId, supplierId,
			...rest }) => rest )" -->
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
							class="input-default"
							v-model="fillInput[index].buyPrice"
						/>
					</fwb-table-cell>
					<fwb-table-cell>
						<!-- 采购数量 -->
						<input
							type="number"
							class="input-default"
							min="1"
							v-model="fillInput[index].num"
						/>
					</fwb-table-cell>
					<fwb-table-cell>
						<!-- 供应商 -->
						<select
							id="countries"
							class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
							v-model="fillInput[index].supplierId"
						>
							<option
								v-for="(list, index) in supplierList"
								:key="index"
								:value="list.id"
								:label="list.name"
							>
								{{ list.name }}
							</option>
						</select>
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
