<script lang="ts" setup>
import useUserInfoStore from '@/store/userInfoStore'
const { userInfo } = useUserInfoStore()
</script>

<template>
	<header>
		<div
			class="flex flex-row justify-between items-center px-5 border-gray-200 bg-gray-50 dark:border-gray-600 dark:bg-gray-700"
		>
			<div class="h-20 w-20">
				<img
					class="w-full h-full object-cover rounded-full"
					src="https://picsum.photos/200/300"
					alt="logo"
				/>
			</div>

			<div class="flex relative group">
				<span class="mr-1">
					<svg
						class="w-6 h-6 text-gray-800 dark:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						width="24"
						height="24"
						fill="currentColor"
						viewBox="0 0 24 24"
					>
						<path
							fill-rule="evenodd"
							d="M12 4a4 4 0 1 0 0 8 4 4 0 0 0 0-8Zm-2 9a4 4 0 0 0-4 4v1a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2v-1a4 4 0 0 0-4-4h-4Z"
							clip-rule="evenodd"
						/>
					</svg>
				</span>
				<span>{{ userInfo.userName }}</span>

				<!-- dropdown -->
				<div
					class="absolute invisible group-hover:visible top-140p -left-140p z-10 bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
				>
					<div
						class="px-4 py-3 text-sm text-gray-900 dark:text-white"
					>
						<div>{{ userInfo.userName }}</div>
						<div class="font-bold truncate mt-1">
							{{ userInfo.roleName }}
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
</template>
