<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { BarSeriesOption } from 'echarts'
import useChart from '@/hooks/useChart'

const loading = ref(false)
// 图表HTML节点
const chart = ref<HTMLDivElement>()
const rankOption: BarSeriesOption | any = {
	title: {
		text: '商品近30天内销售总金额Top10排行榜',
		subtext: '统计口径:每个商品在每天的销售金额累加后排序',
		padding: [10, 10, 10, 20],
		textStyle: {
			color: '#5FB878'
		},
		subtextStyle: {
			color: '#888'
		}
	},
	tooltip: {
		// {a}:系列名 {b}:x轴的值  {c}:y轴的值
		formatter: '{b}的销量:{c}'
	},
	grid: {
		show: true,
		top: 90 //坐标系距离整个chart顶部90px
	},
	legend: {
		show: false
	},
	xAxis: {
		name: '商品名',
		data: ['a', 'b']
	},
	yAxis: {
		type: 'value',
		name: '销量/元',
		axisLine: {
			show: true // 显示y轴坐标轴线
		}
	},
	series: [
		{
			name: 'series-rank',
			type: 'bar',
			data: [1, 2],
			// 设置柱条宽度,避免过少时很宽;过多时重叠
			barMaxWidth: 40
		}
	]
}

let updateTrend2 = () => {}
onMounted(() => {
	const { loadTrend, updateTrend } = useChart(
		chart.value as HTMLDivElement,
		{
			chartType: 'bar',
			chartOptions: rankOption
		},
		{
			url: '/statistic/loadSaleAmount4Goods'
		}
	)
	loadTrend()
	updateTrend2 = () => {
		loading.value = true
		try {
			updateTrend()
		} catch (err) {
			console.log(err)
		} finally {
			loading.value = false
		}
	}
})
</script>

<template>
	<section class="mb-4 ml-4">
		<button @click="updateTrend2" class="button-default">
			<span v-if="loading">加载中</span>
			<span v-else> 刷新 </span>
		</button>
	</section>
	<section>
		<div style="height: 500px" ref="chart"></div>
	</section>
</template>

<style scoped></style>
