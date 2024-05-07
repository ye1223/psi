<script lang="ts" setup>
import { onBeforeMount, reactive, ref } from 'vue'
import { FwbPagination } from 'flowbite-vue'
import { appJsonPost, formGet } from '@/api/request'
import { SearchForm } from '@/ts/interfaces/user.interface'
import { TableData } from '@/ts/interfaces/general.interface'
import { SupplierInfo } from '@/ts/interfaces/supplier.interface'
import Modal from '@/components/UI/Modal.vue'
import Toast from '@/components/UI/Toast'
import UpdateSupplier from './UpdateSupplier.vue'

const tableData = reactive<TableData<SupplierInfo>>({
	total: 0,
	rows: []
})
const searchFormData = reactive<SearchForm>({
	pageNow: 1,
	pageSize: 10,
	name: ''
})
const loadTable = async () => {
	const { data } = await appJsonPost<SearchForm, TableData<SupplierInfo>>({
		url: '/supplier/loadTable',
		data: { ...searchFormData }
	})
	// 按照id排序

	data && data.total
		? data.rows.sort((a, b) => Number(a.id) - Number(b.id))
		: (tableData.rows = [])
	Object.assign(tableData, data)
}

// 组件挂载时加载表格
onBeforeMount(() => {
	loadTable()
})

// 按用户名搜索逻辑
const handleSearch = () => {
	if (!searchFormData.name) {
		Toast.error('请输入供应商')
	}
	loadTable()
}

const addModalVisible = ref(false)
// 新增人员
const handleAddSupplier = () => {
	addModalVisible.value = true
}

const handleClose = () => {
	addModalVisible.value = false
}

const supplierName = ref()
const handleConfirm = () => {
	if (supplierName.value) {
		appJsonPost({
			url: '/supplier/add',
			data: { name: supplierName.value }
		})
			.then(_ => {
				loadTable()
				addModalVisible.value = false
			})
			.catch(resultVO => {
				Toast.error(resultVO.errMsg)
				addModalVisible.value = false
			})
	} else {
		Toast.error('请输入供应商')
		return false
	}
}

// 删除
const handleDelete = (row: SupplierInfo) => {
	formGet({
		url: '/supplier/deleteById',
		params: { id: row.id }
	}).then(_ => {
		loadTable()
	})
}
// 编辑
const editModalVisible = ref(false)
const editRow = ref<SupplierInfo>({
	id: '',
	name: ''
})
const handleEdit = (row: SupplierInfo) => {
	editModalVisible.value = true
	editRow.value = { ...row } // ref -> reactive
}

// 分页
const handlePageChange = (newPageNow: number) => {
	searchFormData.pageNow = newPageNow
	loadTable()
}
</script>

<template>
	<!-- <div>manage user</div> -->
	<div class="flex flex-row items-center mt-3">
		<div class="text-center">
			<button
				type="button"
				class="mx-auto py-2.5 px-5 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
				@click="handleAddSupplier"
			>
				✚新增
			</button>
		</div>

		<div class="relative flex-1 mr-6">
			<div
				class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none"
			>
				<svg
					class="w-4 h-4 text-gray-500 dark:text-gray-400"
					aria-hidden="true"
					xmlns="http://www.w3.org/2000/svg"
					fill="none"
					viewBox="0 0 20 20"
				>
					<path
						stroke="currentColor"
						stroke-linecap="round"
						stroke-linejoin="round"
						stroke-width="2"
						d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"
					/>
				</svg>
			</div>
			<input
				type="search"
				id="default-search"
				class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
				placeholder="供应商名称"
				required
				v-model="searchFormData.name"
			/>
			<button
				type="button"
				class="text-white absolute end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
				@click="handleSearch"
			>
				查询
			</button>
		</div>
	</div>
	<!-- table -->
	<div class="mt-10">
		<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
			<table
				class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400"
			>
				<thead
					class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
				>
					<tr>
						<th scope="col" class="px-6 py-3">系统编号</th>
						<th scope="col" class="px-6 py-3">供应商名称</th>
						<th scope="col" class="px-6 py-3">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr
						class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700"
						v-for="(row, index) in tableData.rows"
						:key="index"
					>
						<th
							scope="row"
							class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
						>
							{{ row.id }}
						</th>
						<td class="px-6 py-4">{{ row.name }}</td>
						<td class="px-6 py-4">
							<span
								class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
								@click="handleEdit(row)"
							>
								编辑
							</span>
							<span
								class="ml-4 font-medium text-red-600 dark:text-red-500 hover:underline"
								@click="handleDelete(row)"
							>
								删除
							</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

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
		:visible="addModalVisible"
		:buttons="['confirm', 'cancel']"
		:cancel="handleClose"
		:confirm="handleConfirm"
	>
		<input
			type="text"
			class="input-default"
			placeholder="请输入供应商名称"
			v-model="supplierName"
		/>
	</Modal>

	<UpdateSupplier
		v-model:editModalVisible="editModalVisible"
		:editRow="editRow"
		:refresh="loadTable"
	/>
</template>

<style scoped></style>
