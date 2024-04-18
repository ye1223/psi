<script lang="ts" setup>
import { reactive, ref } from 'vue'
import * as echarts from 'echarts'
import { appJsonPost } from '@/api/request'
import { GoodsInfo } from '@/ts/interfaces/goods.interface'

interface LoadTrend {
	legendData: []
	series: {
		name: string
		data: number[]
		type: string
		smooth: boolean
	}[]
	xAxisData: string[]
}

// 图表HTML节点
const chart = ref<HTMLDivElement>()

let trendChart: echarts.ECharts
const trendOption = {
	title: {
		text: '商品近30天平均进价走势图',
		subtext: '统计口径:已审核通过的商品在每天的平均进价',
		padding: [10, 10, 10, 20],
		textStyle: {
			color: '#5FB878'
		},
		subtextStyle: {
			color: '#888'
		}
	},
	tooltip: {
		formatter: '{b}号{a}的进价:{c}'
	},
	grid: {
		show: true,
		top: 90 //坐标系距离整个chart顶部90px
	},
	legend: {
		data: ['x商品']
	},
	xAxis: {
		name: '日期',
		type: 'category',
		data: ['2021-12-01', '2021-12-02', '2021-12-03', '2021-12-04']
	},
	yAxis: {
		type: 'value',
		name: '进价',
		axisLine: {
			show: true // 显示y轴坐标轴线
		}
	},
	series: [
		{
			name: 'x商品',
			data: [820, 932, 901, 934, 1290, 1330, 1320],
			type: 'line',
			smooth: true
		}
	]
}

// 加载图表
const loadTrend = () => {
	trendChart = echarts.init(chart.value)
	// true:表示不进行合并,全部更新，老的删除
	trendChart.setOption(trendOption, true)
	updateTrend()
}

// select表单选中的ids
const searchFormData: {
	selectedGoodsIds: number[]
} = reactive({
	selectedGoodsIds: []
})

// 更新图表
let selectedGoods = ref<Pick<GoodsInfo, 'id' | 'name'>[]>([]) //select选中的商品
const updateTrend = () => {
	// 映射选中的商品id - name
	selectedGoods.value = formCfg.goods
		.filter(item => searchFormData.selectedGoodsIds.includes(item.id))
		.map(item => ({
			id: item.id,
			name: item.name
		}))
	appJsonPost<any, LoadTrend>({
		url: '/statistic/loadTrend',
		data: { goodsIds: searchFormData.selectedGoodsIds }
	}).then(result => {
		trendOption.legend.data = result.data.legendData
		trendOption.xAxis.data = result.data.xAxisData
		trendOption.series = result.data.series
		// 设置真实数据
		trendChart.setOption(trendOption, true)
	})
}

const handleDeleteItem = (id: number) => {
	// 改变selectedGoods、selectedGoodsIds
	// 匹配id删除
	selectedGoods.value = selectedGoods.value.filter(item => item.id !== id)
	searchFormData.selectedGoodsIds = searchFormData.selectedGoodsIds.filter(
		item => item !== id
	)
	// 更新图表
	loadTrend()
}

const formCfg = {
	goods: [{ id: -1, name: '' }]
}

// 初始化商品
const initGoods = () => {
	appJsonPost<any, GoodsInfo[]>({
		url: '/goods/selectList',
		data: { name: '' }
	}).then(result => {
		if (!result.data) {
			return
		}
		if (result.data.length === 0) {
			result.data = []
		}
		formCfg.goods = result.data

		// 如果有多个商品
		if (result.data.length > 0) {
			// 默认选中第一个
			searchFormData.selectedGoodsIds.push(result.data[0].id)
			loadTrend()
		}
	})
}
initGoods()
</script>

<template>
	<section class="flex">
		<div class="flex">
			<label
				for="goods"
				class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
			>
				商品
			</label>
			<select
				id="goods"
				class="h-12 overflow-y-auto bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
				v-model="searchFormData.selectedGoodsIds"
				@change="updateTrend"
				multiple
			>
				<option
					v-for="item in formCfg.goods"
					:key="item.id"
					:value="item.id"
				>
					{{ item.name }}
				</option>
			</select>
		</div>

		<ul class="ml-auto flex">
			<li
				class="h-full p-2 ml-2 flex justify-between items-center bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white"
				v-for="item in selectedGoods"
				:key="item.id"
			>
				<div class="mr-2">{{ item.name }}</div>
				<div
					class="hover: cursor-pointer"
					@click="handleDeleteItem(item.id)"
				>
					<svg
						class="w-5 h-5 text-gray-800 dark:text-white"
						aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg"
						width="24"
						height="24"
						fill="none"
						viewBox="0 0 24 24"
					>
						<path
							stroke="currentColor"
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="m15 9-6 6m0-6 6 6m6-3a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
						/>
					</svg>
				</div>
			</li>
		</ul>
	</section>
	<section class="mt-4 overflow-auto">
		<div style="height: 500px" ref="chart"></div>
	</section>
</template>

<style scoped></style>
