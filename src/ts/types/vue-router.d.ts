import 'vue-router'
declare module 'vue-router' {
	interface RouteMeta extends Record<string | number | symbol, unknown> {
		// 关联document.title
		title: string
	}
}
