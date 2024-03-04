<script lang="ts" setup>
import Header from './Layout/Header.vue'
import Sidebar from './Layout/Sidebar.vue'
</script>

<template>
	<section>
		<aside>
			<Header />
		</aside>
		<section class="flex flex-row h-screen">
			<Sidebar />
			<div class="flex-1">
				<router-view></router-view>
			</div>
		</section>
	</section>
</template>

<style scoped></style>
