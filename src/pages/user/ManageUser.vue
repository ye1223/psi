<script lang="ts" setup>
import { onBeforeMount, onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { appJsonPost, formGet } from '@/api/request'
import { SearchForm, TableData } from '@/ts/interfaces/user.interface'
import { UserInfo } from '@/ts/interfaces/userinfo.interface'
import { ROLE_ID } from '@/ts/enums/userinfo.enum'

const tableData = reactive<TableData>({
	total: 0,
	rows: []
})
const searchFormData = reactive<SearchForm>({
	pageNow: 1,
	pageSize: 10,
	userName: ''
})
const loadTable = async () => {
	const { data } = await appJsonPost<SearchForm, TableData>({
		url: '/user/loadTable',
		data: searchFormData
	})
	// 按照id排序
	data.rows.sort((a, b) => Number(a.id) - Number(b.id))

	Object.assign(tableData, data)
}

// 组件挂载时加载表格
onBeforeMount(() => {
	loadTable()
})

onMounted(() => {
	// console.log('挂载时', tableData)
})

// 按用户名搜索逻辑
const handleSearch = () => {
	loadTable()
}

// 新增人员
const router = useRouter()
const handleAddUser = () => {
	router.push('/user/add')
}

// 删除
const handleDelete = (row: UserInfo) => {
	formGet({
		url: '/user/deleteById',
		params: { userId: row.id }
	}).then(_ => {
		loadTable()
	})
}
// 修改密码
const handleEdit = (row: UserInfo) => {
	router.replace({
		path: '/user/toUpdate',
		query: {
			userId: row.id
		}
	})
}
</script>

<template>
	<!-- <div>manage user</div> -->
	<div class="flex flex-row items-center mt-3">
		<div class="mx-8">
			<button
				type="button"
				class="py-2.5 px-5 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
				@click="handleAddUser"
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
				placeholder="按用户名搜索"
				required
				v-model="searchFormData.userName"
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
	<div>
		<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
			<table
				class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400"
			>
				<thead
					class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
				>
					<tr>
						<th scope="col" class="px-6 py-3">系统编号</th>
						<th scope="col" class="px-6 py-3">用户名</th>
						<th scope="col" class="px-6 py-3">角色</th>
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
						<td class="px-6 py-4">{{ row.userName }}</td>
						<td class="px-6 py-4">{{ row.roleName }}</td>
						<td class="px-6 py-4">
							<span
								class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
								@click="handleEdit(row)"
							>
								修改密码
							</span>
							<span
								v-if="row.roleId !== ROLE_ID.ADMIN"
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
</template>

<style scoped></style>
