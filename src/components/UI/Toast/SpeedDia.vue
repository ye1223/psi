<script lang="ts" setup>
import { ref } from 'vue'
const speedDia = ref<HTMLDivElement>()
const handleDiaToggle = () => {
	speedDia.value?.classList.toggle('hidden')
	console.log('222')
}
const handleToggleTheme = () => {
    document.documentElement.classList.toggle('dark')
}
</script>

<template>
	<div data-dial-init class="fixed right-6 bottom-6 group">
		<div
			ref="speedDia"
			id="speed-dial-menu-default"
			class="flex flex-col items-center hidden mb-4 space-y-2"
		>
			<button
                @click="handleToggleTheme"
				type="button"
				data-tooltip-target="tooltip-print"
				data-tooltip-placement="left"
				class="flex justify-center items-center w-[52px] h-[52px] text-gray-500 hover:text-gray-900 bg-white rounded-full border border-gray-200 dark:border-gray-600 shadow-sm dark:hover:text-white dark:text-gray-400 hover:bg-gray-50 dark:bg-gray-700 dark:hover:bg-gray-600 focus:ring-4 focus:ring-gray-300 focus:outline-none dark:focus:ring-gray-400"
			>
				<svg
					class="w-5 h-5 text-gray-800 dark:text-white"
					aria-hidden="true"
					xmlns="http://www.w3.org/2000/svg"
					width="14"
					height="20"
					fill="none"
					viewBox="0 0 14 20"
				>
					<path
						stroke="currentColor"
						stroke-linecap="round"
						stroke-linejoin="round"
						stroke-width="2"
						d="M4 7a3 3 0 0 1 3-3M5 19h4m0-3c0-4.1 4-4.9 4-9A6 6 0 1 0 1 7c0 4 4 5 4 9h4Z"
					/>
				</svg>
				
				<span class="sr-only">切换主题</span>
			</button>
		</div>
		<button
			@click="handleDiaToggle"
			type="button"
			data-dial-toggle="speed-dial-menu-default"
			aria-controls="speed-dial-menu-default"
			aria-expanded="false"
			class="flex items-center justify-center text-white bg-blue-700 rounded-full w-14 h-14 hover:bg-blue-800 dark:bg-blue-600 dark:hover:bg-blue-700 focus:ring-4 focus:ring-blue-300 focus:outline-none dark:focus:ring-blue-800"
		>
			<svg
				class="w-5 h-5 transition-transform group-hover:rotate-45"
				aria-hidden="true"
				xmlns="http://www.w3.org/2000/svg"
				fill="none"
				viewBox="0 0 18 18"
			>
				<path
					stroke="currentColor"
					stroke-linecap="round"
					stroke-linejoin="round"
					stroke-width="2"
					d="M9 1v16M1 9h16"
				/>
			</svg>
			<span class="sr-only">打开菜单</span>
		</button>
	</div>
</template>
