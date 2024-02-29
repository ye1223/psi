/* 
    shims-vue.d.ts 文件是一个类型声明文件，用于告诉 TypeScript 编译器如何处理 Vue 的单文件组件（SFC）和其他自定义模块。
    它可以为 Vue 的单文件组件和其他非 TypeScript 模块提供类型信息，以便在 TypeScript 项目中使用它们时能够正确地进行类型检查和代码提示。
*/

// 声明一个模块，用于匹配所有以 ".vue" 结尾的文件
declare module '*.vue' {
	import { ComponentOptions } from 'vue'
	const componentOptions: ComponentOptions
	export default componentOptions
}
