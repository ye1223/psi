import { ref } from 'vue'
import { defineStore } from 'pinia'
import { formGet } from '@/api/request'
import { UserInfo } from '@/ts/interfaces/userinfo.interface'

const useUserInfoStore = defineStore('userInfo', () => {
	const userInfo = ref<UserInfo>(
		JSON.parse(localStorage.getItem('userInfo') as string)
	)
	
	const fetchUserInfo = async () => {
		const { data } = await formGet<UserInfo>({
			url: '/user/getUserByToken'
		})
		userInfo.value = data

		storeUserInfo(userInfo.value)
	}

	const storeUserInfo = (payload: UserInfo) => {
		if (userInfo) {
			// userInfo.value = payload
			localStorage.setItem('userInfo', JSON.stringify(payload))
		} else {
			localStorage.removeItem('userInfo')
		}
	}
	return {
		userInfo,
		fetchUserInfo,
		storeUserInfo
	}
})

export default useUserInfoStore
