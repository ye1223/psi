<script lang="ts" setup>
import { watch, ref } from 'vue'
import { Rules } from 'async-validator'
import Modal from '@/components/UI/Modal.vue'
import Toast from '@/components/UI/Toast'
import useFormValidator from '@/hooks/useFormValidator'
import { SupplierInfo } from '@/ts/interfaces/supplier.interface'
import { appJsonPost } from '@/api/request'
const props = defineProps<{
	editModalVisible: boolean
	editRow: SupplierInfo
	refresh: Function
}>()
const emit = defineEmits<{
	'update:editModalVisible': [val: boolean]
}>()
const form = ref<SupplierInfo>({
	id: '-9999',
	name: ''
})

watch(
	() => props.editRow,
	newVal => {
		form.value = newVal
	},
	{ immediate: true, deep: true }
)

const rules: Rules = {
	name: {
		type: 'string',
		required: true
	}
}
const handleConfirm = async () => {
	const { pass, execute } = useFormValidator(rules, form)
	await execute()
	if (pass.value) {
		appJsonPost({
			url: '/supplier/updateById',
			data: form.value
		})
			.then(_ => {
				// loadTable()
				props.refresh()
				// 关闭modal
				emit('update:editModalVisible', false)
			})
			.catch(resultVO => {
				Toast.error(resultVO.errMsg)
			})
	} else {
		Toast.error('校验失败')
	}
}

const handleClose = () => {
	emit('update:editModalVisible', false)
}
</script>

<template>
	<Modal
		:visible="props.editModalVisible"
		:buttons="['cancel', 'confirm']"
		:confirm="handleConfirm"
		:cancel="handleClose"
	>
		<form class="space-y-4 md:space-y-6" onsubmit="return false">
			<div>
				<label
					for="supplier_id"
					class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>
					编号
				</label>
				<input
					type="text"
					name="supplier_id"
					class="input-disabled"
					disabled
					readonly
					v-model="form.id"
				/>
			</div>
			<div>
				<label for="supplier_name" class="label-default">
					供应商名称
				</label>
				<input
					type="text"
					name="supplier_name"
					v-model="form.name"
					class="input-default"
					placeholder="请输入供应商名称"
				/>
			</div>
		</form>
	</Modal>
</template>

<style scoped></style>
