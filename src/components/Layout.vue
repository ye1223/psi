<script lang="ts" setup>
import Header from './Layout/Header.vue'
import Sidebar from './Layout/Sidebar.vue'
</script>

<template>
	<section class="w-full h-full flex flex-col">
		<aside>
			<Header />
		</aside>
		<section class="grow flex flex-row">
			<Sidebar />
			<div class="flex-1 p-5 overflow-auto">
				<router-view></router-view>
			</div>
		</section>
	</section>
</template>

<style scoped></style>
