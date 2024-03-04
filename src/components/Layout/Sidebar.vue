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
	subMenuItemActive.value = meunitem
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
		class="h-full w-fit md:w-1/4 lg:w-1/5 px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800"
	>
		<ul class="space-y-2 font-medium">
			<li v-if="userInfo.roleId === ROLE_ID.ADMIN">
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.BASIC_INFO)"
				>
					<svg
						class="w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
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
				<ul
					v-show="subMenuItemActive === MENUITEM.BASIC_INFO"
					class="py-2 space-y-2"
				>
					<li>
						<router-link
							to="/user/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							员工管理
						</router-link>
					</li>
					<li>
						<router-link
							to="/supplier/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							供应商管理
						</router-link>
					</li>
					<li>
						<router-link
							to="/goods/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							商品管理
						</router-link>
					</li>
				</ul>
			</li>

			<li
				v-if="
					userInfo.roleId === ROLE_ID.ADMIN ||
					userInfo.roleId === ROLE_ID.AUDIT
				"
			>
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.RET)"
				>
					<svg
						class="w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
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
				<ul
					class="py-2 space-y-2"
					v-show="subMenuItemActive === MENUITEM.RET"
				>
					<li>
						<router-link
							to="/purchase/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							进货管理
						</router-link>
					</li>
					<li>
						<router-link
							to="/returnGoods/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							退货管理
						</router-link>
					</li>
				</ul>
			</li>
			<li
				v-if="
					userInfo.roleId === ROLE_ID.ADMIN ||
					userInfo.roleId === ROLE_ID.INVENTORY
				"
			>
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.PURCHASE_REFUND)"
				>
					<svg
						class="w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
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
				<ul
					class="py-2 space-y-2"
					v-show="subMenuItemActive === MENUITEM.PURCHASE_REFUND"
				>
					<li>
						<router-link
							to="/purchase/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							销售管理
						</router-link>
					</li>
					<li>
						<router-link
							to="/returnGoods/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							退款管理
						</router-link>
					</li>
				</ul>
			</li>
			<li
				v-if="
					userInfo.roleId === ROLE_ID.ADMIN ||
					userInfo.roleId === ROLE_ID.SALE
				"
			>
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.AUDIT)"
				>
					<svg
						class="w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
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
				<ul
					class="py-2 space-y-2"
					v-show="subMenuItemActive === MENUITEM.AUDIT"
				>
					<li>
						<router-link
							to="/purchase/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							销售管理
						</router-link>
					</li>
					<li>
						<router-link
							to="/returnGoods/manage"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							退款管理
						</router-link>
					</li>
				</ul>
			</li>
			<li v-if="userInfo.roleId === ROLE_ID.ADMIN">
				<button
					type="button"
					class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
					aria-controls="dropdown-example"
					@click="handleSubMenuToggle(MENUITEM.STATISTIC)"
				>
					<svg
						class="w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
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
				<ul
					class="py-2 space-y-2"
					v-show="subMenuItemActive === MENUITEM.STATISTIC"
				>
					<li>
						<router-link
							to="/statistics/purchasePrice4Goods"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							商品进价波动
						</router-link>
					</li>
					<li>
						<router-link
							to="/statistics/saleNum4Goods"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							商品购买量
						</router-link>
					</li>
					<li>
						<router-link
							to="/statistics/ret4Supplier"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							供应商进货占比
						</router-link>
					</li>
					<li>
						<router-link
							to="/statistics/saleAmount4Goods"
							class="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
						>
							销售额统计
						</router-link>
					</li>
				</ul>
			</li>

			<li>
				<a
					href="javascript:;"
					class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
				>
					<svg
						class="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
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
				</a>
			</li>
		</ul>
	</div>
</template>
