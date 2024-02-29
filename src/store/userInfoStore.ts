import { ref } from 'vue'
import { defineStore } from 'pinia'
import { formGet } from '@/api/request'
import { UserInfo } from '@/ts/interfaces/userinfo.interface'

const useUserInfoStore = defineStore('userInfo', () => {

	const userInfo = ref<UserInfo>({
		id: '',
		userName: '',
		roleId: -999,
		roleName: ''
	})

	const fetchUserInfo = async () => {
		const { data } = await formGet<UserInfo>({
			url: '/user/getUserByToken'
		})
		userInfo.value = data

		storeUserInfo(userInfo.value)
	}

	const storeUserInfo = (payload: UserInfo) => {
		userInfo.value = payload
	}
	return {
		userInfo,
		fetchUserInfo,
		storeUserInfo
	}
})

export default useUserInfoStore
