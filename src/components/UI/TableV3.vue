<!-- 🔧fixed checkbox多选模式 全选 单选 -->
<script lang="ts" setup>
import { watch, ref, toRaw } from 'vue'

type label = [string, string]
interface Props {
	// no?: boolean // system serial number
	labels: label[] // table head
	list: Array<any> // table row data
	operation?: boolean // whether have operation
	selectAll?: boolean //
	checkbox?: boolean // 多选框
	singleSelectMode?: boolean
}
const props = withDefaults(defineProps<Props>(), {
	// no: true,
	operation: false,
	selectAll: false,
	singleSelectMode: true
})
const emit = defineEmits<{
	offerSelectedData: [row: any] // 具名元组语法
}>()

const selectedRows = ref(new Set<any>())
// const singleSelectMode = ref(false) // false多选模式

const handleSelectionChange = (row: any) => {
	if (props.singleSelectMode) {
		// 单选模式
		selectedRows.value.clear()
		selectedRows.value.add(row.id)
		emit('offerSelectedData', [...selectedRows.value])
		console.log('单选', ...selectedRows.value)
	} else {
		// 多选模式
		// const rowIndex = props.list.findIndex(item => item === row)
		const rowIndex = row.id
		// console.log('row', row, rowIndex)
		if (rowIndex !== -1) {
			if (selectedRows.value.has(rowIndex)) {
				selectedRows.value.delete(rowIndex)
			} else {
				selectedRows.value.add(rowIndex)
			}
		}
		// console.log([...selectedRows.value])
		// 将选中的id传出去
		emit('offerSelectedData', Array.from(selectedRows.value))
	}
}

// 全选
const handleAllChanged = (event: Event) => {
	/* const target = event.target as HTMLInputElement
	if (target.checked) {
		if (props.singleSelectMode) {
			selectedRows.value.clear()
		}
		selectedRows.value.clear()
		props.list.forEach((_, index) => selectedRows.value.add(index))
	} else {
		selectedRows.value.clear()
	} */
	const target = event.target as HTMLInputElement
	selectedRows.value.clear() // 无论如何先清空已选集合

	if (target.checked && !props.singleSelectMode) {
		// 仅在多选模式且全选被选中时执行
		props.list.forEach(row => {
			selectedRows.value.add(row.id) // 使用行的唯一标识符作为键
		})
	}

	// 在多选模式下，可以通过emit发送全选后的数据，如有需要
	if (!props.singleSelectMode) {
		emit('offerSelectedData', Array.from(selectedRows.value))
	}
	// console.log(Array.from(selectedRows.value))
}

/* const toggleSingleSelectMode = () => {
	singleSelectMode.value = !singleSelectMode.value
} */

const isSelected = (rowIndex: number) => selectedRows.value.has(rowIndex)

const reCompolist = ref([])
watch(
	() => props.list,
	() => {
		;(reCompolist.value as any) = props.list.map(row => {
			const newRow: Record<string, any> = {}
			props.labels.forEach(([key, _]) => {
				newRow[key] = row[key]
			})
			return newRow
		})
	},
	{
		immediate: true
	}
)
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
					<th class="px-6 py-3" v-if="props.checkbox">
						<!-- 全选checkbox -->
						<div
							class="flex items-center"
							v-if="!props.singleSelectMode"
						>
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
						<div class="flex items-center" v-else></div>
					</th>
					<!-- 					<th scope="col" v-if="props.no" class="px-6 py-3">
						系统编号
					</th> -->
					<th
						scope="col"
						class="px-6 py-3"
						v-for="(label, index) in props.labels"
						:key="index"
					>
						{{ label[1] }}
					</th>
					<th class="px-6 py-3" v-if="props.operation">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr
					class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700"
					v-for="(originalRow, index) in props.list"
					:key="index"
				>
					<!-- <th
						scope="row"
						class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
					>
						{{ row.id }}
					</th> -->
					<th class="text-center" v-if="props.checkbox">
						<input
							id="checkbox"
							type="checkbox"
							value=""
							class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
							:checked="isSelected(originalRow.id)"
							@change="handleSelectionChange(originalRow)"
						/>
					</th>
					<th
						v-for="(item, i) in reCompolist[index]"
						:key="i"
						class="px-6 py-4"
					>
						<!-- <span v-if="row[item]">{{ row[item] }}</span> -->
						{{ item }}
					</th>

					<th class="px-1 py-4" v-if="props.operation">
						<slot :row="toRaw(originalRow)"></slot>
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
