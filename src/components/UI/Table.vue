<script lang="ts" setup>
import { GoodsInfo } from '@/ts/interfaces/goods.interface'

/* interface TableRow<T extends Record<string, any>> {
	data: T
} */
const props = defineProps<{
	labels: string[]
	tableData: {
		total: number
		rows: GoodsInfo[]
	}
	operation?: boolean
	selectAll?: boolean
}>()

const handleAllChanged = () => {}
</script>

<template>
	<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
		<table
			class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400"
		>
			<thead
				class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
			>
				<tr>
					<th class="px-6 py-3" v-if="props.selectAll">
						<!-- 全选checkbox -->
						<div class="flex items-center">
							<input
								id="checkbox"
								type="checkbox"
								value=""
								class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
								@change="handleAllChanged"
							/>
							<label
								for="checkbox"
								class="ms-2 text-gray-900 dark:text-gray-300"
							>
								全选
							</label>
						</div>
					</th>
					<th scope="col" class="px-6 py-3">系统编号</th>
					<th
						scope="col"
						class="px-6 py-3"
						v-for="(label, index) in props.labels"
						:key="index"
					>
						{{ label }}
					</th>
					<th class="px-6 py-3" v-if="props.operation">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr
					class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700"
					v-for="(row, index) in props.tableData.rows"
					:key="index"
				>
					<!-- <th
						scope="row"
						class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
					>
						{{ row.id }}
					</th> -->
					<th class="text-center" v-if="props.selectAll">
						<input
							id="checkbox"
							type="checkbox"
							value=""
							class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
						/>
					</th>
					<th
						v-for="(item, index) in row"
						:key="index"
						class="px-6 py-4"
					>
						<!-- <span v-if="row[item]">{{ row[item] }}</span> -->
						{{ item }}
					</th>

					<th class="px-1 py-4" v-if="props.operation">
						<slot :row="row"></slot>
					</th>

					<!-- 					<td class="px-6 py-4">
						<span
							class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
						>
							修改密码
						</span>
					</td> -->
				</tr>
			</tbody>
		</table>
	</div>
</template>

<style scoped>
.item:first-child {
	@apply px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white;
}
</style>
