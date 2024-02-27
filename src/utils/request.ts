import axios, {
	InternalAxiosRequestConfig,
	AxiosRequestConfig,
	AxiosError,
	AxiosInstance,
	AxiosResponse
} from 'axios'
import Toast from '@/components/UI/Toast'
import { drawToken } from "@/utils/storage";
import { BASE_URL } from '@/utils/constants'

// axios基本配置
const baseConfig: AxiosRequestConfig = {
	baseURL: BASE_URL,
	timeout: 1000 * 60 * 10, //ms
	headers: {
		'Content-Type': 'application/json' //默认内容类型
	}
}

// 配置axios实例拦截器方法
const addInterceptors = (req: AxiosInstance) => {
	// 1.请求拦截器
	req.interceptors.request.use(
		(config: InternalAxiosRequestConfig) => {
			config.headers['Authorization'] = drawToken()
			if ((config.headers["Content-Type"] as string).toLowerCase().indexOf("application/json") > -1) {
				if (!config.data) {
					// 后端如果是@RequestBody接受,data又没有的话后端会报错
					config.data = JSON.stringify({ "_": new Date().getTime() })
				}
			} else {
				if (!config.params) {
					// 默认传个时间戳
					config.params = {};
					config.params['_'] = new Date().getTime()
				}
			}
			return config
		},
		(error: AxiosError) => {
			console.log('请求发送失败', error)
		}
	)

	// 2.响应拦截器
	req.interceptors.response.use((response: AxiosResponse) => {
		// 直接返回服务端返回的结果
		let resultVO = response.data;
		console.log('response interceptor' ,response)
		if (resultVO.success) {
			return resultVO;
		} else {
			Toast.error(`业务异常:"${resultVO.errMsg}`)
			console.log("业务异常:", resultVO.errMsg);
			// 直接自己返回Promise
			return Promise.reject(resultVO);
		}
	})
}

const formGetInstance = axios.create(baseConfig)
addInterceptors(formGetInstance)

const formPostInstance = axios.create({
	...baseConfig,
	method: 'post',
})
addInterceptors(formPostInstance)

const appJsonPostInstance = axios.create({
	...baseConfig,
	method: 'post',
	headers: {
		"Content-Type": "application/json"
	}
})
addInterceptors(appJsonPostInstance)

export { formGetInstance, formPostInstance, appJsonPostInstance }
