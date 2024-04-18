<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { PieSeriesOption } from 'echarts'
import useChart from '@/hooks/useChart'
// 图表HTML节点
const chart = ref<HTMLDivElement>()
const pieOption: PieSeriesOption | any = {
	title: {
		text: '供应商退货占比统计',
		subtext: '统计口径:该供应商被退货次数/供应商退货总次数',
		left: 'center'
	},
	tooltip: {
		trigger: 'item',
		formatter: '{b}占比:{c}%'
	},
	legend: {
		orient: 'vertical',
		left: 'left'
	},
	series: [
		{
			name: '供应商退货占比统计',
			type: 'pie',
			radius: '50%',
			data: [
				{ value: 1048, name: '搜索引擎' },
				{ value: 735, name: '直接访问' },
				{ value: 580, name: '邮件营销' },
				{ value: 484, name: '联盟广告' },
				{ value: 300, name: '视频广告' }
			],
			emphasis: {
				itemStyle: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}
	]
}
onMounted(() => {
	const { loadTrend } = useChart(
		chart.value as HTMLDivElement,
		{
			chartType: 'pie',
			chartOptions: pieOption
		},
		{
			url: '/statistic/loadRet4Supplier'
		}
	)
	loadTrend()
})
</script>

<template>
	<section>
		<div style="height: 500px" ref="chart"></div>
	</section>
</template>

<style scoped></style>
