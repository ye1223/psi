/// <reference types="vite/client" />

// 定义import.meta.env的接口
interface ImportMetaEnv {
    readonly VITE_NODE_ENV: string
    readonly VITE_API_BASE_URL: string
}