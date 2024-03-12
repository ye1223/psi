<script lang="ts" setup>
import { reactive } from 'vue'
import Search from '@/components/UI/Search.vue'
import Table from '@/components/UI/Table.vue'
import useTable from '@/hooks/useTable'
import { GoodsInfo } from '@/ts/interfaces/goods.interface'
import { SearchForm } from '@/ts/interfaces/user.interface'
import Toast from '@/components/UI/Toast'
import { formGet } from '@/api/request'
import { useRouter } from 'vue-router'

const searchFormData = reactive<SearchForm>({
	pageNow: 1,
	pageSize: 10,
	name: ''
})
const { tableData, loadTable } = useTable<GoodsInfo>({
	fetchUrl: '/goods/loadTable'
})
// 一来加载Table
loadTable()

const handleSearch = async (searchText: string) => {
	if (searchText === '') {
		Toast.error('请正确输入')
		loadTable()
	}
	searchFormData.name = searchText
	loadTable(searchFormData)
}

const handleDelete = (row: GoodsInfo) => {
	formGet({
		url: '/goods/deleteById',
		params: { id: String(row.id) }
	})
		.then(_ => {
			loadTable()
		})
		.catch(result => {
			Toast.error(result.errMsg)
		})
}

const router = useRouter()
const handleAdd = () => {
	router.replace('/goods/add')
}
</script>

<template>
	<section class="flex flex-row items-center mt-3">
		<div class="text-center">
			<button type="button" class="button-default" @click="handleAdd">
				✚新增
			</button>
		</div>
		<Search placeholder="输入商品名称" :handleSearch="handleSearch" />
	</section>

	<section class="mt-8">
		<Table
			:labels="['商品名', '产地', '销售价(￥)', '库存', '单位', '规格']"
			:tableData="tableData"
			:operation="true"
			:selectAll="false"
		>
			<template #default="{ row }">
				<span
					class="ml-4 font-medium text-red-600 dark:text-red-500 hover:underline"
					@click="handleDelete(row)"
				>
					删除
				</span>
			</template>
		</Table>
	</section>
</template>

<style scoped></style>
