//@ts-nocheck
import { createApp, h } from 'vue'
import ToastComponent from './template/Toast.vue'

interface ToastProps {
	visible: boolean
	type: 'success' | 'error'
	message: string
}

const ToastContainer = createApp({
	render() {
		return h(ToastComponent, this.toastProps)
	},
	data() {
		return {
			toastProps: {
				visible: false,
				type: '',
				message: '' 
			} as unknown as ToastProps
		}
	}
})

const toast = ToastContainer.mount(document.createElement('div'))

document.body.appendChild(toast.$el)

const Toast = {
	success(message: string) {
		toast.toastProps = {
			visible: true,
			type: 'success',
			message
		}
		setTimeout(() => {
			toast.toastProps.visible = false
		}, 3000)
	},
	error(message: string) {
		toast.toastProps = {
			visible: true,
			type: 'error',
			message
		}
		setTimeout(() => {
			toast.toastProps.visible = false
		}, 3000)
	}
}

export default Toast
