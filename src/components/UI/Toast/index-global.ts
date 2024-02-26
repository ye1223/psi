// @ts-nocheck
import { App, createApp, h } from 'vue'
import ToastComponent from './template/Toast.vue'

const ToastPlugin = {
	install(app: App) {
		const ToastContainer = createApp({
			render() {
				return h(ToastComponent, this.toastProps)
			},
			data() {
				return {
					toastProps: {
						visible: true,
						type: '',
						message: ''
					}
				}
			}
		})

		const toast = ToastContainer.mount(document.createElement('div'))

        console.warn(toast)
        console.log(toast.$el)

		document.body.appendChild(toast.$el)

		app.config.globalProperties.$toast = {
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
	}
}

export default ToastPlugin
