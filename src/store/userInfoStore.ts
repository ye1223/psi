import { ref } from 'vue'
import { defineStore } from 'pinia'
import { formGet } from '@/api/request'
import { UserInfo } from '@/ts/interfaces/userinfo.interface'


const useUserInfoStore = defineStore('userInfo', async () => {
	// 如果回话存储没有USER_INFO_KEY，使用默认的
	const userInfo = ref<UserInfo | null>(null)

	userInfo.value = {
		id: 'string',
		userName: 'string',
		roleId: 1,
		roleName: 'string'
	}
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
