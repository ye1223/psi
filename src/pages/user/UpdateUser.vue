<script lang="ts" setup>
import { onBeforeMount, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { Rules } from 'async-validator'
import { appJsonPost } from '@/api/request'
import Toast from '@/components/UI/Toast'
import useFormValidator from '@/hooks/useFormValidator'
import { UserInfo } from '@/ts/interfaces/userinfo.interface'
import useUserInfoStore from '@/store/userInfoStore'

const form = ref({
	id: '',
	userName: '',
	newPwd: '',
	oldPwd: ''
})

const route = useRoute()

const fetchUserInfo = async () => {
	const { data } = await appJsonPost<any, UserInfo>({
		url: '/user/getUserById',
		data: { userId: route.query.userId }
	})
	form.value.id = data.id
	form.value.userName = data.userName
}

// 挂载数据
onBeforeMount(() => {
	fetchUserInfo()
})

const router = useRouter()
const rules: Rules = {
	oldPwd: {
		type: 'string',
		required: true
	},
	newPwd: {
		type: 'string',
		required: true
	}
}

const { userInfo } = useUserInfoStore()
const handleConfirmEdit = async () => {
	const { pass, execute } = useFormValidator(rules, form)
	await execute()
	if (pass.value) {
		console.log(form.value)
		appJsonPost({
			url: '/user/updatePwd',
			data: form.value
		})
			.then(_ => {
				if (userInfo.id === form.value.id) {
					router.replace('/')
				} else {
					router.replace('/user/manage')
				}
			})
			.catch(resultVO => {
				Toast.error(`${resultVO.errMsg}`)
			})
	} else {
		Toast.error('表单验证错误')
	}
}
</script>

<template>
	<form class="max-w-sm mx-auto">
		<div class="mb-5">
			<label
				for="userId"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>用户编号</label
			>
			<input
				type="text"
				id="userId"
				aria-label="disabled input 2"
				class="bg-gray-200 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 cursor-not-allowed"
				disabled
				readonly
				v-model="form.id"
			/>
		</div>
		<div class="mb-5">
			<label
				for="userName"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
			>
				用户名
			</label>
			<input
				id="userName"
				class="bg-gray-200 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 cursor-not-allowed"
				disabled
				readonly
				v-model="form.userName"
			/>
		</div>
		<div class="mb-5">
			<label
				for="password"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
			>
				旧密码
			</label>
			<input
				type="password"
				id="password"
				class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
				required
				v-model="form.oldPwd"
			/>
		</div>
		<div class="mb-5">
			<label
				for="password"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
			>
				新密码
			</label>
			<input
				type="password"
				id="password"
				class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
				required
				v-model="form.newPwd"
			/>
		</div>

		<button
			type="button"
			class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
			@click="handleConfirmEdit"
		>
			确认修改
		</button>
	</form>
</template>

<style scoped></style>
