<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps<{
	// label?: string
	for: string
	required?: boolean
}>()

const hasRequiredStar = computed(() => props.required)
</script>

<template>
	<label
		:for="props.for"
		class="label-default"
	>
		<!-- {{ label }} -->
        <slot></slot>
        <span v-if="hasRequiredStar" class="text-red-500">*</span>
	</label>
</template>
