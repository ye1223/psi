<script lang="ts" setup>
import { ref } from 'vue'
import { VERIFIED_STATUS } from '@/ts/enums/input.enum'
import InputVerified from '@/utils/inputVerified'
const modelValue = defineModel<string>()
const props = defineProps<{
	id?: string
	type?: string
	name?: string
	class?: string
	placeholder?: string
}>()
const isVerified = ref(VERIFIED_STATUS.DEFAULT)
const clearInput = () => {
	isVerified.value = VERIFIED_STATUS.DEFAULT
}

const CancelVerified = (isVerified.value = VERIFIED_STATUS.DEFAULT)
defineExpose({
	CancelVerified
})
</script>
<template>
	<input
		v-model="modelValue"
		:type="props.type"
		:name="props.name"
		:id="props.id"
		:class="[
			props.class,
			InputVerified(isVerified) ? 'input-default' : 'input-error'
		]"
		@input="clearInput"
		:placeholder="props.placeholder"
	/>
</template>
