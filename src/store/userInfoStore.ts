import { ref } from 'vue'
import { defineStore } from 'pinia'
import { formGet } from '@/api/request'
import { UserInfo } from '@/ts/interfaces/userinfo.interface'


const useUserInfoStore = defineStore('userInfo', async () => {
	// 如果回话存储没有USER_INFO_KEY，使用默认的
	const userInfo = ref<UserInfo | null>(null)
	const fetchUserInfo = async () => {
		(userInfo.value) = await formGet<UserInfo>({
			url: ''
		})
		updateUserInfo(userInfo.value)
	  }
	
	  const updateUserInfo = (payload: UserInfo) => {
		userInfo.value = payload
	  }
	return {
		userInfo,
		fetchUserInfo,
		updateUserInfo
	}
})

export default useUserInfoStore
