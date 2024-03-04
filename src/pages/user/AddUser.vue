<script lang="ts" setup>
import { onBeforeMount, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { Rules } from 'async-validator'
import { appJsonPost, formGet } from '@/api/request'
import { UserAddForm, UserRole } from '@/ts/interfaces/user.interface'
import useFormValidator from '@/hooks/useFormValidator'
import { ROLE_ID } from '@/ts/enums/userinfo.enum'
import Toast from '@/components/UI/Toast'

const roles = reactive<UserRole[]>([])
const getListRoles = async () => {
	const { data } = await formGet<UserRole>({
		url: '/role/listRoles'
	})
	Object.assign(roles, data)
}
onBeforeMount(() => {
	getListRoles()
})

const rules: Rules = {
	userName: {
		type: 'string',
		required: true
	},
	pwd: {
		type: 'string',
		required: true
	},
	roleId: {
		type: 'number',
		required: false
	}
}

const form = ref<UserAddForm>({
	userName: '',
	pwd: '',
	roleId: ROLE_ID.INVENTORY //默认
})

const router = useRouter()
const handleAddUser = async () => {
	const { pass, execute } = useFormValidator(rules, form)
	await execute()
	if (pass.value) {
		appJsonPost<UserAddForm, any>({
			url: '/user/add',
			data: form.value
		})
			.then(_ => {
				router.replace('/user/manage')
			})
			.catch(err => {
				Toast.error(err.errMsg)
			})
	} else {
		Toast.error('账号验证错误')
	}
}
</script>

<template>
	<form class="max-w-sm mx-auto">
		<div class="mb-5">
			<label
				for="userName"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
				>用户名</label
			>
			<input
				id="userName"
				class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
				required
				v-model="form.userName"
			/>
		</div>
		<div class="mb-5">
			<label
				for="password"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
			>
				密码
			</label>
			<input
				type="password"
				id="password"
				class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
				required
				v-model="form.pwd"
			/>
		</div>
		<div class="mb-5">
			<label
				for="countries"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
			>
				角色
			</label>
			<select
				id="countries"
				class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
				v-model="form.roleId"
			>
				<option
					v-for="(item, index) in roles"
					:key="index"
					:value="item.roleId"
				>
					{{ item.roleName }}
				</option>
			</select>
		</div>
		<button
			type="button"
			class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
			@click="handleAddUser"
		>
			添加
		</button>
	</form>
</template>

<style scoped></style>
