import { createRouter, createWebHashHistory, RouterOptions } from 'vue-router'
import routes from './config'
import { IGNORE_URLS, ROLE_AUTH } from "@/utils/constants"
import { drawToken, drawUserInfo } from "@/utils/storage";


// RouterOptions是路由选项类型
const options: RouterOptions = {
	history: createWebHashHistory(),
	routes
}

const router = createRouter(options)
router.beforeEach((toRoute, _, next) => {
	let targetTitle = toRoute.matched[toRoute.matched.length - 1].meta.title;
	console.log(toRoute)
	console.log(targetTitle)
	// toRoute.path:/user/manage
	if (IGNORE_URLS.includes(toRoute.path)) {
		if (targetTitle) {
			// @ts-ignore
			document.title = targetTitle;
		}
		// 说明当前轻轻的路径不需要拦截
		return next();
	} else {
		if (drawToken()) {
			// @ts-ignore
			let authUrls = ROLE_AUTH[drawUserInfo().roleId];
			if (!authUrls) {
				return next({ path: '/' });
			}
			// 检查配置的权限数组
			if (authUrls.includes("*")) { // 超级角色
				if (targetTitle) {
					// @ts-ignore
					document.title = targetTitle;
				}
				return next();
			} else {
				let match = false;
				// ['/purchase',"/returnGoods"]
				for (let item of authUrls) {
					if (toRoute.path.indexOf(item) > -1) {
						match = true;
						// arr.forEach不能匹配后break,return无效的,这里用for of
						break;
					}
				}
				if (match) {
					if (targetTitle) {
						// @ts-ignore
						document.title = targetTitle;
					}
					return next();
				}
			}
		}
		// 无权限指定跳转到哪里
		return next({ path: '/' });
	}
});

export default router
