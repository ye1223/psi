<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { MENUITEM } from '@/utils/constants'
import { clearStorage } from '@/utils/storage'
import { ROLE_ID } from '@/ts/enums/userinfo.enum'
import useUserInfoStore from '@/store/userInfoStore'

// 控制子菜单展开
const subMenuItemActive = ref('')

const handleSubMenuToggle = (meunitem: string) => {
	subMenuItemActive.value !== meunitem
		? (subMenuItemActive.value = meunitem)
		: (subMenuItemActive.value = '')
}

const router = useRouter()
const handleExit = () => {
	clearStorage()
	router.replace('/')
}

const { userInfo } = useUserInfoStore()
</script>
<template>
	<div
		class="w-fit md:w-1/4 lg:w-1/5 px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800"
	>
		<ul class="space-y-2 font-medium">
			<li>
				<a
					href="javascript:;"
					class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
				>
					<svg
						class="w-6 h-6 text-gray-500 dark:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="currentColor"
						viewBox="0 0 24 24"
					>
						<path
							fill-rule="evenodd"
							d="M11.3 3.3a1 1 0 0 1 1.4 0l6 6 2 2a1 1 0 0 1-1.4 1.4l-.3-.3V19a2 2 0 0 1-2 2h-3a1 1 0 0 1-1-1v-3h-2v3c0 .6-.4 1-1 1H7a2 2 0 0 1-2-2v-6.6l-.3.3a1 1 0 0 1-1.4-1.4l2-2 6-6Z"
							clip-rule="evenodd"
						/>
					</svg>

					<router-link
						to="/index"
						class="flex-1 ml-3 whitespace-nowrap"
					>
						首页
					</router-link>
				</a>
			</li>
			<li v-if="userInfo.roleId === ROLE_ID.ADMIN">
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 Transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.BASIC_INFO)"
				>
					<svg
						class="w-6 h-6 text-gray-500 dark:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="currentColor"
						viewBox="0 0 24 24"
					>
						<path
							fill-rule="evenodd"
							d="M2 12a10 10 0 1 1 20 0 10 10 0 0 1-20 0Zm9.4-5.5a1 1 0 1 0 0 2 1 1 0 1 0 0-2ZM10 10a1 1 0 1 0 0 2h1v3h-1a1 1 0 1 0 0 2h4a1 1 0 1 0 0-2h-1v-4c0-.6-.4-1-1-1h-2Z"
							clip-rule="evenodd"
						/>
					</svg>

					<span class="flex-1 ml-3 text-left whitespace-nowrap">
						基础资料
					</span>
					<svg
						class="w-3 h-3"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="none"
						viewBox="0 0 10 6"
					>
						<path
							stroke="currentColor"
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="m1 1 4 4 4-4"
						/>
					</svg>
				</button>
				<Transition name="fade">
					<ul
						v-show="subMenuItemActive === MENUITEM.BASIC_INFO"
						class="py-2 space-y-2"
					>
						<li>
							<router-link
								to="/user/manage"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								员工管理
							</router-link>
						</li>
						<li>
							<router-link
								to="/supplier/manage"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								供应商管理
							</router-link>
						</li>
						<li>
							<router-link
								to="/goods/manage"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								商品管理
							</router-link>
						</li>
					</ul>
				</Transition>
			</li>

			<li
				v-if="
					userInfo.roleId === ROLE_ID.ADMIN ||
					userInfo.roleId === ROLE_ID.INVENTORY
				"
			>
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 Transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.RET)"
				>
					<svg
						class="w-6 h-6 text-gray-500 dark:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="currentColor"
						viewBox="0 0 24 24"
					>
						<path
							fill-rule="evenodd"
							d="M4 4a2 2 0 0 0-2 2v9c0 .6.4 1 1 1h.5v.5a3.5 3.5 0 1 0 7-.5h3v.5a3.5 3.5 0 1 0 7-.5h.5c.6 0 1-.4 1-1v-4l-.1-.4-2-4A1 1 0 0 0 19 6h-5a2 2 0 0 0-2-2H4Zm14.2 11.6.3.9a1.5 1.5 0 1 1-.3-1Zm-10 0 .3.9a1.5 1.5 0 1 1-.3-1ZM14 10V8h4.4l1 2H14Z"
							clip-rule="evenodd"
						/>
					</svg>

					<span class="flex-1 ml-3 text-left whitespace-nowrap">
						进退货
					</span>
					<svg
						class="w-3 h-3"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="none"
						viewBox="0 0 10 6"
					>
						<path
							stroke="currentColor"
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="m1 1 4 4 4-4"
						/>
					</svg>
				</button>

				<Transition name="fade">
					<ul
						class="py-2 space-y-2"
						v-show="subMenuItemActive === MENUITEM.RET"
					>
						<li>
							<router-link
								to="/purchase/manage"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								进货管理
							</router-link>
						</li>
						<li>
							<router-link
								to="/returnGoods/manage"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								退货管理
							</router-link>
						</li>
					</ul>
				</Transition>
			</li>
			<li
				v-if="
					userInfo.roleId === ROLE_ID.ADMIN ||
					userInfo.roleId === ROLE_ID.SALE
				"
			>
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 Transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.PURCHASE_REFUND)"
				>
					<svg
						class="w-6 h-6 text-gray-500 dark:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="currentColor"
						viewBox="0 0 24 24"
					>
						<path
							fill-rule="evenodd"
							d="M14 7h-4v3a1 1 0 1 1-2 0V7H6a1 1 0 0 0-1 1L4 19.7A2 2 0 0 0 6 22h12c1 0 2-1 2-2.2L19 8c0-.5-.5-.9-1-.9h-2v3a1 1 0 1 1-2 0V7Zm-2-3a2 2 0 0 0-2 2v1H8V6a4 4 0 1 1 8 0v1h-2V6a2 2 0 0 0-2-2Z"
							clip-rule="evenodd"
						/>
					</svg>

					<span class="flex-1 ml-3 text-left whitespace-nowrap">
						销售与退款
					</span>
					<svg
						class="w-3 h-3"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="none"
						viewBox="0 0 10 6"
					>
						<path
							stroke="currentColor"
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="m1 1 4 4 4-4"
						/>
					</svg>
				</button>

				<Transition name="fade">
					<ul
						class="py-2 space-y-2"
						v-show="subMenuItemActive === MENUITEM.PURCHASE_REFUND"
					>
						<li>
							<router-link
								to="/saleOrder/manage"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								销售管理
							</router-link>
						</li>
						<li>
							<router-link
								to="/refund/manage"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								退款管理
							</router-link>
						</li>
					</ul>
				</Transition>
			</li>
			<li
				v-if="
					userInfo.roleId === ROLE_ID.ADMIN ||
					userInfo.roleId === ROLE_ID.AUDIT
				"
			>
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 Transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.AUDIT)"
				>
					<svg
						class="w-6 h-6 text-gray-500 dark:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="currentColor"
						viewBox="0 0 24 24"
					>
						<path
							fill-rule="evenodd"
							d="M12 14a3 3 0 0 1 3-3h4a2 2 0 0 1 2 2v2a2 2 0 0 1-2 2h-4a3 3 0 0 1-3-3Zm3-1a1 1 0 1 0 0 2h4v-2h-4Z"
							clip-rule="evenodd"
						/>
						<path
							fill-rule="evenodd"
							d="M12.3 3.3a1 1 0 0 1 1.4 0L16.4 6h-2.8l-1.3-1.3a1 1 0 0 1 0-1.4Zm.1 2.7L9.7 3.3a1 1 0 0 0-1.4 0L5.6 6h6.8ZM4.6 7A2 2 0 0 0 3 9v10c0 1.1.9 2 2 2h12a2 2 0 0 0 2-2h-4a5 5 0 0 1 0-10h4a2 2 0 0 0-1.5-2h-13Z"
							clip-rule="evenodd"
						/>
					</svg>

					<span class="flex-1 ml-3 text-left whitespace-nowrap">
						账务审核
					</span>
					<svg
						class="w-3 h-3"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="none"
						viewBox="0 0 10 6"
					>
						<path
							stroke="currentColor"
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="m1 1 4 4 4-4"
						/>
					</svg>
				</button>

				<Transition name="fade"
					><ul
						class="py-2 space-y-2"
						v-show="subMenuItemActive === MENUITEM.AUDIT"
					>
						<li>
							<router-link
								to="/audit/purchase"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								进货审核
							</router-link>
						</li>
						<li>
							<router-link
								to="/audit/ret"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								退货审核
							</router-link>
						</li>
					</ul>
				</Transition>
			</li>
			<li v-if="userInfo.roleId === ROLE_ID.ADMIN">
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 Transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.STATISTIC)"
				>
					<svg
						class="w-5 h-5 text-gray-500 Transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="currentColor"
						viewBox="0 0 22 21"
					>
						<path
							d="M16.975 11H10V4.025a1 1 0 0 0-1.066-.998 8.5 8.5 0 1 0 9.039 9.039.999.999 0 0 0-1-1.066h.002Z"
						/>
						<path
							d="M12.5 0c-.157 0-.311.01-.565.027A1 1 0 0 0 11 1.02V10h8.975a1 1 0 0 0 1-.935c.013-.188.028-.374.028-.565A8.51 8.51 0 0 0 12.5 0Z"
						/>
					</svg>
					<span class="flex-1 ml-3 text-left whitespace-nowrap">
						统计分析
					</span>
					<svg
						class="w-3 h-3"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="none"
						viewBox="0 0 10 6"
					>
						<path
							stroke="currentColor"
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="m1 1 4 4 4-4"
						/>
					</svg>
				</button>
				<Transition name="fade">
					<ul
						class="py-2 space-y-2"
						v-show="subMenuItemActive === MENUITEM.STATISTIC"
					>
						<li>
							<router-link
								to="/statistics/purchasePrice4Goods"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								商品进价波动
							</router-link>
						</li>
						<li>
							<router-link
								to="/statistics/saleNum4Goods"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								商品购买量
							</router-link>
						</li>
						<li>
							<router-link
								to="/statistics/ret4Supplier"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								供应商进货占比
							</router-link>
						</li>
						<li>
							<router-link
								to="/statistics/saleAmount4Goods"
								class="flex items-center w-full p-2 text-gray-900 Transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
							>
								销售额统计
							</router-link>
						</li>
					</ul>
				</Transition>
			</li>

			<li>
				<span
					class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
				>
					<svg
						class="flex-shrink-0 w-5 h-5 text-gray-500 Transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						fill="none"
						viewBox="0 0 18 16"
					>
						<path
							stroke="currentColor"
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="M1 8h11m0 0L8 4m4 4-4 4m4-11h3a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-3"
						/>
					</svg>
					<span
						class="flex-1 ml-3 whitespace-nowrap"
						@click="handleExit"
					>
						退出
					</span>
				</span>
			</li>
		</ul>
	</div>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
	transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
	opacity: 0;
	/* max-height: 0; */
}
</style>
