import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig(({ mode, command }) => {
  // console.log(mode, command)
  const env = loadEnv(mode, process.cwd())
  // console.log(env, process.cwd()) //项目绝对地址
  const BASE_URL = env.VITE_API_BASE_URL

  return {
    base: './',
    plugins: [vue(), vueJsx()],
    // envPrefix:"APP_",//APP_  默认是VITE_为开头名
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src')
      }
    },
    server: {
      proxy: {
        '/api': {
          target: BASE_URL,
          changeOrigin: true,
          rewrite: path => path.replace(/^\/api/, '')
        }
      }
    },
    worker: {
      format: 'es'
    }
  }
})

